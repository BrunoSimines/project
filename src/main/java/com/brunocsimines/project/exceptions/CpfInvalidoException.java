package com.brunocsimines.project.exceptions;

public class CpfInvalidoException extends Exception{
    public CpfInvalidoException() {
        super("Por favor insira um cpf válido.");
    }
}
