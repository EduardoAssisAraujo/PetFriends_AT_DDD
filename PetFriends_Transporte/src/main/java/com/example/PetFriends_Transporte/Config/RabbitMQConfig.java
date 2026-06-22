package com.example.PetFriends_Transporte.Config;



import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Exercício 3.3 — Classe de configuração para tratamento de mensagens
 * do PetFriends_Transporte.
 * <p>
 * Declara a fila pela qual este serviço recebe eventos do
 * PetFriends_Almoxarifado (originados no PetFriends_Pedido), além do
 * conversor de mensagens JSON.
 */
@Configuration
public class RabbitMQConfig {

    /**
     * Fila alimentada pelo Almoxarifado após reservar estoque com sucesso.
     * Consumida pelo Transporte para criar a entrega.
     */
    public static final String FILA_TRANSPORTE =
            "petfriends.transporte.pedidos";

    @Bean
    public Queue filaTransporte() {
        return new Queue(FILA_TRANSPORTE, true);
    }

    // Necessário para serializar/deserializar JSON nas mensagens
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

}
