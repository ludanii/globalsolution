package br.com.fiap.globalsolution.model;

public enum Funcao {
    COZINHEIRO("Cozinheiro"),
    LIMPEZA("Limpeza"),
    ENFERMEIRO("Enfermeiro"),
    MEDICO("Médico"),
    PSICOLOGO("Psicólogo"),
    ENGENHEIRO("Engenheiro"),
    ASSISTENTE_SOCIAL("Assistente Social"),
    LOGISTICA("Logística"),
    MOTORISTA("Motorista"),
    SEGURANCA("Segurança"),
    MONITOR_INFANTIL("Monitor Infantil"),
    TRADUTOR("Tradutor"),
    CUIDADOR_DE_IDOSOS("Cuidador de Idosos"),
    CUIDADOR_DE_ANIMAIS("Cuidador de Animais"),
    ATENDIMENTO_ADMINISTRATIVO("Atendimento Administrativo"),
    APOIO_TECNICO_TI("Apoio Técnico em TI"),
    MONTAGEM_DE_ESTRUTURAS("Montagem de Estruturas"),
    CAPTACAO_DE_RECURSOS("Captação de Recursos"),
    COORDENADOR_DE_ABRIGO("Coordenador de Abrigo");

    private final String descricao;

    Funcao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
