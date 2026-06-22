package com.example.PetFriends_Almoxarifado.Dtos;



/**
 * Representa o corpo da requisição para simular a criação de um pedido.
 * Em produção, este payload viria do PetFriends_Pedido via evento de
 * domínio; aqui ele é fornecido manualmente via REST para fins de teste.
 */
public record SimularPedidoRequest(
        Long pedidoId,
        Long produtoId,
        Long clienteId,
        Integer quantidade,
        EnderecoEntregaDTO enderecoEntrega
) {
}
