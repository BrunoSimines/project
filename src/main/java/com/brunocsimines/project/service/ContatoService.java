package com.brunocsimines.project.service;

import com.brunocsimines.project.domain.Contato;
import com.brunocsimines.project.exceptions.CampoVazioException;
import com.brunocsimines.project.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Stream;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public Contato salvar(Contato contato) throws CampoVazioException, Exception {
        try {
            contato.validarContato();
            return contatoRepository.save(contato);
        } catch (Exception e) {
           throw new Exception(e);
        }
    }

    public Contato buscarPorId(Long id) {
        return contatoRepository.findById(id).orElse(null);
    }

    public Contato alterarContato(Contato contato) {
        return contatoRepository.save(contato);
    }

    public void deletarContato(Long id) {
       contatoRepository.deleteById(id);
    }
}
