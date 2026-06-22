package com.example.PetFriends_Almoxarifado.Publisher;



import com.example.PetFriends_Almoxarifado.Configs.RabbitMQConfig;
import com.example.PetFriends_Almoxarifado.Events.PedidoProntoParaEntregaEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * Publica o evento de pedido pronto para entrega na fila consumida
 * pelo PetFriends_Transporte, completando o fluxo sequencial
 * Pedido -> Almoxarifado -> Transporte.
 */
@Service
public class PedidoProntoParaEntregaPublisher {

    private final RabbitTemplate rabbitTemplate;

    public PedidoProntoParaEntregaPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publicar(PedidoProntoParaEntregaEvent event) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.FILA_TRANSPORTE, event);

        System.out.println("Evento publicado para o Transporte");
        System.out.println("Pedido: " + event.getPedidoId());
    }
}
