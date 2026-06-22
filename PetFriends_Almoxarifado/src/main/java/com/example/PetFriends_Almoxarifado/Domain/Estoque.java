package com.example.PetFriends_Almoxarifado.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estoques")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long produtoId;

    @Embedded
    private Quantidade quantidade;

    public Estoque() {
    }

    public Estoque(Long produtoId, Quantidade quantidade) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public void reservar(Integer quantidadeReservada) {
        if (quantidade.getValor() < quantidadeReservada) {
            throw new RuntimeException("Estoque insuficiente");
        }

        quantidade = new Quantidade(
                quantidade.getValor() - quantidadeReservada);
    }


}
