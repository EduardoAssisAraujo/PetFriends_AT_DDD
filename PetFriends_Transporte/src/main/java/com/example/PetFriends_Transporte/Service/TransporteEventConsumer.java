package com.example.PetFriends_Transporte.Service;


import com.example.PetFriends_Transporte.Config.RabbitMQConfig;
import com.example.PetFriends_Transporte.Domain.EnderecoEntrega;
import com.example.PetFriends_Transporte.Domain.Entrega;
import com.example.PetFriends_Transporte.Events.PedidProntoParaEntregaEnvent;
import com.example.PetFriends_Transporte.Repository.EntregaRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TransporteEventConsumer {

    private final EntregaRepository entregaRepository;

    public TransporteEventConsumer(
            EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    @RabbitListener(
            queues = RabbitMQConfig.FILA_TRANSPORTE)
    public void receberPedidoProntoParaEntrega(
            PedidProntoParaEntregaEnvent event) {

        EnderecoEntrega endereco = new EnderecoEntrega(
                event.getEnderecoEntrega().getRua(),
                event.getEnderecoEntrega().getNumero(),
                event.getEnderecoEntrega().getBairro(),
                event.getEnderecoEntrega().getCidade(),
                event.getEnderecoEntrega().getCep()
        );

        Entrega entrega =
                new Entrega(
                        event.getPedidoId(),
                        endereco);

        entregaRepository.save(entrega);

        System.out.println(
                "Nova entrega criada.");

        System.out.println(
                "Pedido: " + event.getPedidoId());

        System.out.println(
                "Cliente: " + event.getClienteId());

        System.out.println(
                "Endereço: " + event.getEnderecoEntrega());
    }
}