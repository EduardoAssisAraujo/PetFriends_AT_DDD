package com.example.PetFriends_Almoxarifado.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoEntregaDTO {
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String cep;

    public EnderecoEntregaDTO() {}
    @Override
    public String toString() {
        return rua + ", " + numero + " - " + bairro + ", " + cidade + " - " + cep;
    }


}
