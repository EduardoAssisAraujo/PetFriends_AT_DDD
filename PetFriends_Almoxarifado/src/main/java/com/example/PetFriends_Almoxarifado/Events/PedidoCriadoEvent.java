package com.example.PetFriends_Almoxarifado.Events;

import com.example.PetFriends_Almoxarifado.Dtos.EnderecoEntregaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Evento publicado pelo PetFriends_Pedido quando um novo pedido é criado.
 * <p>
 * Exercício 2.3 — Contém o payload completo necessário para o Almoxarifado
 * reservar o estoque do produto pedido, sem precisar de uma chamada
 * síncrona de volta ao serviço de Pedidos.
 * <p>
 * O campo enderecoEntrega não é usado pela regra de reserva de estoque,
 * mas é mantido aqui pois o fluxo é sequencial: o Almoxarifado precisa
 * repassar essa informação ao publicar o PedidoProntoParaEntregaEvent
 * para o Transporte.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoCriadoEvent {

    private Long pedidoId;

    private Long produtoId;

    private Long clienteId;

    private Integer quantidade;

    private EnderecoEntregaDTO enderecoEntrega;

    private LocalDateTime dataCriacao;

}