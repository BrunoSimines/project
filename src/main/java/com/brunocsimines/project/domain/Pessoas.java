package com.brunocsimines.project.domain;

import com.brunocsimines.project.exceptions.CampoVazioException;
import com.brunocsimines.project.exceptions.CpfInvalidoException;
import com.brunocsimines.project.exceptions.DataNascimentoException;
import com.brunocsimines.project.exceptions.EmailInvalidoException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pessoas {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="nome", nullable = false)
    private String nome;

    @Column(name="cpf", nullable = false)
    private String cpf;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="data_nascimento", nullable = false)
    private String dataNascimento;

    @OneToMany(cascade=CascadeType.PERSIST)
    @JoinColumn(name="fk_id_pessoas")
    private List<Contato> contatos;

    public void validarEmail() throws EmailInvalidoException {
        if(this.email.split("@").length != 2 ) {
            throw new EmailInvalidoException();
        }

    }
    public Boolean validarPessoa() throws CpfInvalidoException, CampoVazioException, EmailInvalidoException {
        if(this.email==null || this.nome==null || this.cpf == null || this.contatos.size() < 1) {
            throw new CampoVazioException();
        }
        this.validarEmail();
        if(!validarCPF()) {
            throw new CpfInvalidoException();
        }
        return true;
    }

    public boolean validarCPF() {
        if (this.cpf.equals("00000000000") ||
                this.cpf.equals("11111111111") ||
                this.cpf.equals("22222222222") || this.cpf.equals("33333333333") ||
                this.cpf.equals("44444444444") || this.cpf.equals("55555555555") ||
                this.cpf.equals("66666666666") || this.cpf.equals("77777777777") ||
                this.cpf.equals("88888888888") || this.cpf.equals("99999999999") ||
                (this.cpf.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                num = (int)(this.cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);

            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(this.cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            if ((dig10 == this.cpf.charAt(9)) && (dig11 == this.cpf.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

    public void setDataNascimento(String dataNascimento) throws DataNascimentoException, ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date compararData = formato.parse(dataNascimento);
        Date dataAtual = new Date();
        System.out.println(dataAtual);
        if(dataAtual.after(compararData)) {
            this.dataNascimento = dataNascimento;
        } else {
            throw new DataNascimentoException();
        }
    }
}
