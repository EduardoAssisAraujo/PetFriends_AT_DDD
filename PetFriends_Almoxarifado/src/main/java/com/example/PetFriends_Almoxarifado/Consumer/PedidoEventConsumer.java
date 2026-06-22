package com.example.PetFriends_Almoxarifado.Consumer;

import com.example.PetFriends_Almoxarifado.Configs.RabbitMQConfig;
import com.example.PetFriends_Almoxarifado.Events.PedidoCriadoEvent;

import com.example.PetFriends_Almoxarifado.service.EstoqueService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoEventConsumer {

    private final EstoqueService estoqueService;

    public PedidoEventConsumer(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @RabbitListener(queues = RabbitMQConfig.FILA_PEDIDOS)
    public void receberPedidoCriado(PedidoCriadoEvent event) {

        System.out.println("Pedido recebido pelo Almoxarifado: " + event.getPedidoId());

        estoqueService.processarPedido(event);
    }
}