package com.example.PetFriends_Almoxarifado.Controller;



import com.example.PetFriends_Almoxarifado.Configs.RabbitMQConfig;
import com.example.PetFriends_Almoxarifado.Dtos.SimularPedidoRequest;
import com.example.PetFriends_Almoxarifado.Events.PedidoCriadoEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Endpoint de TESTE para simular a publicação do PedidoCriadoEvent.
 * <p>
 * Em produção este evento seria publicado pelo microsserviço
 * PetFriends_Pedido (que ainda não existe). Este controller existe
 * apenas para permitir testar o fluxo event-driven completo
 * (Almoxarifado reserva estoque -> publica para o Transporte)
 * sem depender desse terceiro serviço.
 */
@RestController
@RequestMapping("/pedidos")
public class SimularPedidoController {

    private final RabbitTemplate rabbitTemplate;

    public SimularPedidoController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/simular")
    public ResponseEntity<String> simular(@RequestBody SimularPedidoRequest request) {

        PedidoCriadoEvent event = new PedidoCriadoEvent(
                request.pedidoId(),
                request.produtoId(),
                request.clienteId(),
                request.quantidade(),
                request.enderecoEntrega(),
                LocalDateTime.now()
        );

        rabbitTemplate.convertAndSend(RabbitMQConfig.FILA_PEDIDOS, event);

        return ResponseEntity.ok(
                "Evento PedidoCriadoEvent publicado para o pedido " + request.pedidoId());
    }
}
