package br.com.fiap.globalsolution.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "global_aviso")
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAviso;
    @Enumerated(EnumType.STRING)
    private Ocorrencia ocorrencia;
    @Enumerated(EnumType.STRING)
    private Gravidade gravidade;
    @Enumerated(EnumType.STRING)
    private Bairro bairro;
    private LocalDateTime dataHora;

    public Long getIdAviso() {
        return idAviso;
    }

    public void setIdAviso(Long idAviso) {
        this.idAviso = idAviso;
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public Gravidade getGravidade() {
        return gravidade;
    }

    public void setGravidade(Gravidade gravidade) {
        this.gravidade = gravidade;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
