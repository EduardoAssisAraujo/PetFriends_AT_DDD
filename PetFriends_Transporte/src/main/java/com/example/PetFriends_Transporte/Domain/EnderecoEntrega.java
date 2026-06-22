package com.example.PetFriends_Transporte.Domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class EnderecoEntrega {

    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String cep;

    protected EnderecoEntrega() {
    }

    public EnderecoEntrega(
            String rua,
            String numero,
            String bairro,
            String cidade,
            String cep) {

        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
    }


}
