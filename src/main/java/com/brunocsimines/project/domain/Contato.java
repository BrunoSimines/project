package com.brunocsimines.project.domain;

import com.brunocsimines.project.exceptions.EmailInvalidoException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contato {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="nome", nullable = false)
    private String nome;

    @Column(name="telefone", nullable = false)
    private String telefone;

    @Column(name="email", nullable = false)
    private String email;

    public void setEmail(String email) throws EmailInvalidoException {
        validarEmail(email);
    }

    public void validarEmail(String email) throws EmailInvalidoException {
       if(email !=null) {
           if (email.split("@").length != 2) {
               throw new EmailInvalidoException();
           } else {
               this.email = email;
           }
       } else {
           throw  new EmailInvalidoException();
       }
    }

    public Boolean validarContato() {
        if (this.getEmail()==null || this.getNome() ==null || this.getTelefone() == null) {
            return false;
        }
        return true;
    }
}
