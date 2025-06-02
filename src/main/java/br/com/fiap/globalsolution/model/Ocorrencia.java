package br.com.fiap.globalsolution.model;

public enum Ocorrencia {
    ENCHENTE("Enchente"),
    DESLIZAMENTO("Deslizamento"),
    ALAGAMENTO("Alagamento"),
    FALTA_DE_ENERGIA("Falta de Energia"),
    FALTA_DE_AGUA("Falta de Água"),
    INCENDIO("Incêndio"),
    ACIDENTE("Acidente"),
    VENTO_FORTE("Vento Forte"),
    DESABAMENTO("Desabamento"),
    CONTAMINACAO("Contaminação"),
    SURTO_DE_DOENCA("Surto de Doença"),
    EXPLOSAO("Explosão"),
    PANE_EM_SISTEMA("Pane em Sistema"),
    EVACUACAO("Evacuação"),
    BLOQUEIO_DE_VIA("Bloqueio de Via"),
    OUTROS("Outros");

    private final String descricao;

    Ocorrencia(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
