package br.com.fiap.globalsolution.model;

public enum TipoDeficiencia {
    FISICA("Deficiência Física"),
    AUDITIVA("Deficiência Auditiva"),
    VISUAL("Deficiência Visual"),
    INTELECTUAL("Deficiência Intelectual"),
    MULTIPLA("Deficiência Múltipla"),
    PSICOSSOCIAL("Deficiência Psicossocial / Transtorno Mental"),
    NAO_POSSUI("Não possui deficiência");

    private final String descricao;

    TipoDeficiencia(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
