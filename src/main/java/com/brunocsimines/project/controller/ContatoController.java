package com.brunocsimines.project.controller;

import com.brunocsimines.project.domain.Contato;
import com.brunocsimines.project.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contato")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @PostMapping
    public ResponseEntity salvar(@RequestBody Contato contato) {
        try {
         return ResponseEntity.status(201).body(contatoService.salvar(contato));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity consultaCpf(@PathVariable Long id) {
        Contato contato = contatoService.buscarPorId(id);
        if(contato==null) {
            return ResponseEntity.status(400).body("Nenhum contato encontrado com este id");
        }
        return ResponseEntity.status(200).body(contato);
    }

    @PutMapping()
    public ResponseEntity alterar(@RequestBody Contato contato) {
        try {
            return ResponseEntity.status(200).body(contatoService.alterarContato(contato));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deletar(@RequestParam Long id) {
        try {
            contatoService.deletarContato(id);
            return ResponseEntity.status(200).body("Contato deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
