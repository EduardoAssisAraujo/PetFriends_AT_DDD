package com.example.PetFriends_Almoxarifado.Domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Quantidade {

    private Integer valor;

    protected Quantidade() {
    }

    public Quantidade(Integer valor) {

        if (valor < 0) {
            throw new IllegalArgumentException(
                    "Quantidade não pode ser negativa");
        }

        this.valor = valor;
    }

    public Integer getValor() {
        return valor;
    }
}
