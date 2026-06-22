package com.example.PetFriends_Transporte.Events;


import com.example.PetFriends_Transporte.Dtos.EnderecoEntregaDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class PedidProntoParaEntregaEnvent {

    private Long pedidoId;
    private Long clienteId;
    private EnderecoEntregaDTO enderecoEntrega;
    private LocalDateTime dataCriacao;

    public PedidProntoParaEntregaEnvent() {}

}
