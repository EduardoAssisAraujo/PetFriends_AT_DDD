package com.example.PetFriends_Transporte.Domain;

import com.example.PetFriends_Transporte.Enums.StatusEntrega;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "entregas")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId;

    @Embedded
    private EnderecoEntrega endereco;

    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    public Entrega() {
    }

    public Entrega(Long pedidoId,
                   EnderecoEntrega endereco) {

        this.pedidoId = pedidoId;
        this.endereco = endereco;
        this.status = StatusEntrega.EM_TRANSITO;
    }

    public void finalizarEntrega() {
        this.status = StatusEntrega.ENTREGUE;
    }


}
