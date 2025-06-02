package br.com.fiap.globalsolution.model;

public enum Gravidade {
    BAIXA("Baixa"),
    MEDIA("Média"),
    ALTA("Alta"),
    CRITICA("Crítica");

    private final String descricao;

    Gravidade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
