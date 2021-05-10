package com.brunocsimines.project.exceptions;

public class EmailInvalidoException extends Exception{
    public EmailInvalidoException() {
        super("Por favor, insira um e-mail válido!");
    }
}
