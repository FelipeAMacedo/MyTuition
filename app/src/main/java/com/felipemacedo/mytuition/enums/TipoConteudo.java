package com.example.felipemacedo.mytuition.enums;

/**
 * Created by felipemacedo on 24/09/17.
 */

public enum TipoConteudo {
    TEXTO(1), QUESTAO(2), ANIMACAO(3);

    private final int valor;

    TipoConteudo(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
