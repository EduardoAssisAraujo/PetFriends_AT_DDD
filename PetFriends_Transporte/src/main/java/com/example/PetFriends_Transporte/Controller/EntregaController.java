package com.example.PetFriends_Transporte.Controller;




import com.example.PetFriends_Transporte.Domain.Entrega;
import com.example.PetFriends_Transporte.Repository.EntregaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Endpoint para consultar as entregas criadas, permitindo confirmar
 * visualmente que o fluxo event-driven completo funcionou:
 * Almoxarifado reservou estoque e publicou o evento, e o Transporte
 * o consumiu e persistiu a entrega correspondente.
 */
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private final EntregaRepository entregaRepository;

    public EntregaController(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    @GetMapping
    public List<Entrega> listar() {
        return entregaRepository.findAll();
    }
}
