package com.brunocsimines.project.controller;

import com.brunocsimines.project.domain.Pessoas;
import com.brunocsimines.project.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity salvar(@RequestBody Pessoas pessoa) {
        try {
            pessoa.validarPessoa();
            return ResponseEntity.status(201).body(pessoaService.salvar(pessoa));
        } catch (Exception e) {
           return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity listarTodos() {
        try{
            return ResponseEntity.status(200).body(pessoaService.listarPessoas());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/{cpf}")
    public ResponseEntity buscarPessoaCpf(@RequestParam String cpf) {
        try{
            return ResponseEntity.status(200).body(pessoaService.buscarPorCpf(cpf));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity alterarPessoa(@RequestBody Pessoas pessoa) {
        try{
            return ResponseEntity.status(200).body(pessoaService.alterarPessoa(pessoa));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarPessoa(@RequestParam Long id) {
        try {
            pessoaService.deletePessoa(id);
            return ResponseEntity.status(200).body("Deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
