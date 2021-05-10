package com.brunocsimines.project.exceptions;

public class CampoVazioException extends Exception{
    public CampoVazioException () {
        super("Existem campos vazios, por favor verique novamente.");
    }
}
