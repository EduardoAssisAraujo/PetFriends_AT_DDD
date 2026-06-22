package com.example.PetFriends_Almoxarifado.Consumer;

import com.example.PetFriends_Almoxarifado.Configs.RabbitMQConfig;
import com.example.PetFriends_Almoxarifado.Domain.Estoque;
import com.example.PetFriends_Almoxarifado.Events.PedidoCriadoEvent;
import com.example.PetFriends_Almoxarifado.Events.PedidoProntoParaEntregaEvent;
import com.example.PetFriends_Almoxarifado.Publisher.PedidoProntoParaEntregaPublisher;
import com.example.PetFriends_Almoxarifado.Repository.EstoqueRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Exercício 3.2 — Serviço que recebe os eventos do PetFriends_Pedido.
 * <p>
 * Ao receber um PedidoCriadoEvent, reserva o estoque do produto pedido
 * e, em caso de sucesso, publica um PedidoProntoParaEntregaEvent para
 * o PetFriends_Transporte.
 */
@Service
public class PedidoEventConsumer {

    private final EstoqueRepository estoqueRepository;
    private final PedidoProntoParaEntregaPublisher publisher;

    public PedidoEventConsumer(EstoqueRepository estoqueRepository,
                               PedidoProntoParaEntregaPublisher publisher) {
        this.estoqueRepository = estoqueRepository;
        this.publisher = publisher;
    }

    @RabbitListener(queues = RabbitMQConfig.FILA_PEDIDOS)
    public void receberPedidoCriado(PedidoCriadoEvent event) {
        System.out.println("Pedido recebido pelo Almoxarifado");
        System.out.println("Pedido: " + event.getPedidoId());
        System.out.println("Produto: " + event.getProdutoId());
        System.out.println("Cliente: " + event.getClienteId());
        System.out.println("Quantidade: " + event.getQuantidade());

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
