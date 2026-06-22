package com.example.PetFriends_Almoxarifado.service;


import com.example.PetFriends_Almoxarifado.Domain.Estoque;
import com.example.PetFriends_Almoxarifado.Events.PedidoCriadoEvent;
import com.example.PetFriends_Almoxarifado.Events.PedidoProntoParaEntregaEvent;
import com.example.PetFriends_Almoxarifado.Publisher.PedidoProntoParaEntregaPublisher;
import com.example.PetFriends_Almoxarifado.Repository.EstoqueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final PedidoProntoParaEntregaPublisher publisher;

    public EstoqueService(EstoqueRepository estoqueRepository,
                          PedidoProntoParaEntregaPublisher publisher) {
        this.estoqueRepository = estoqueRepository;
        this.publisher = publisher;
    }

    @Transactional
    public void processarPedido(PedidoCriadoEvent event) {

        Estoque estoque = estoqueRepository
                .findByProdutoId(event.getProdutoId())
                .orElseThrow(() -> new IllegalStateException(
                        "Estoque não encontrado para o produto: " + event.getProdutoId()));

        estoque.reservar(event.getQuantidade());
        estoqueRepository.save(estoque);

        PedidoProntoParaEntregaEvent proximoEvento =
                new PedidoProntoParaEntregaEvent(
                        event.getPedidoId(),
                        event.getClienteId(),
                        event.getEnderecoEntrega(),
                        event.getDataCriacao()
                );

        publisher.publicar(proximoEvento);
    }
}
