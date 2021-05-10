package com.brunocsimines.project.service;

import com.brunocsimines.project.domain.Pessoas;
import com.brunocsimines.project.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;


    public Pessoas salvar(Pessoas pessoas) throws Exception {
        try {
            return pessoaRepository.save(pessoas);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Pessoas> listarPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoas buscarPorCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf);
    }

    public Pessoas alterarPessoa(Pessoas pessoas) {
        return pessoaRepository.save(pessoas);
    }

    public void deletePessoa(Long id) {
        pessoaRepository.deleteById(id);
    }
}
