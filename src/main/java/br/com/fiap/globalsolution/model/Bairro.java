package br.com.fiap.globalsolution.model;

public enum Bairro {
    BELA_VISTA("Bela Vista"),
    PINHEIROS("Pinheiros"),
    VILA_MARIANA("Vila Mariana"),
    MOEMA("Moema"),
    ITAIM_BIBI("Itaim Bibi"),
    JARDINS("Jardins"),
    LIBERDADE("Liberdade"),
    TATUAPE("Tatuapé"),
    SANTANA("Santana"),
    TUCURUVI("Tucuruvi"),
    MORUMBI("Morumbi"),
    LAPA("Lapa"),
    BROOKLIN("Brooklin"),
    IPIRANGA("Ipiranga"),
    PERDIZES("Perdizes"),
    VILA_OLIMPIA("Vila Olímpia");

    private final String descricao;

    Bairro(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

