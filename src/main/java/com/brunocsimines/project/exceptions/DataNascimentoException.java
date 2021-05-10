package com.brunocsimines.project.exceptions;

public class DataNascimentoException extends Exception {
    public DataNascimentoException() {
        super("Data de nascimento não pode ser superior a data atual");
    }
}
