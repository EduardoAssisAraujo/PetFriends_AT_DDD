package com.example.PetFriends_Almoxarifado.Configs;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Exercício 3.1 — Classe de configuração para tratamento de mensagens
 * do PetFriends_Almoxarifado.
 * <p>
 * Declara a fila pela qual este serviço recebe eventos do
 * PetFriends_Pedido, e a fila pela qual publica eventos para o
 * PetFriends_Transporte, além do conversor de mensagens JSON.
 */
@Configuration
public class RabbitMQConfig {

    /**
     * Fila alimentada pelo PetFriends_Pedido.
     * Consumida pelo Almoxarifado para reservar estoque.
     */
    public static final String FILA_PEDIDOS =
            "petfriends.almoxarifado.pedidos";

    /**
     * Fila alimentada pelo Almoxarifado após reservar estoque com sucesso.
     * Consumida pelo PetFriends_Transporte para criar a entrega.
     */
    public static final String FILA_TRANSPORTE =
            "petfriends.transporte.pedidos";

    @Bean
    public Queue filaPedidos() {
        return new Queue(FILA_PEDIDOS, true);
    }

    @Bean
    public Queue filaTransporte() {
        return new Queue(FILA_TRANSPORTE, true);
    }

    // Necessário para serializar/deserializar JSON nas mensagens
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

}
