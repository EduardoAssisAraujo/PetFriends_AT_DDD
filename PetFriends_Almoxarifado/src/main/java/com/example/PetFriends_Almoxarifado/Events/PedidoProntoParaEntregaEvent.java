package com.example.PetFriends_Almoxarifado.Events;

import com.example.PetFriends_Almoxarifado.Dtos.EnderecoEntregaDTO;
import com.example.PetFriends_Almoxarifado.Dtos.SimularPedidoRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Evento publicado pelo Almoxarifado quando o estoque de um pedido é
 * reservado com sucesso, sinalizando que o pedido está pronto para ser
 * entregue.
 * <p>
 * Exercício 2.4 — Não inclui dados de produto/quantidade, pois o
 * Transporte não precisa dessa informação para realizar a entrega.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProntoParaEntregaEvent {

    private Long pedidoId;

    private Long clienteId;

    private EnderecoEntregaDTO enderecoEntrega;

    private LocalDateTime dataCriacao;
}