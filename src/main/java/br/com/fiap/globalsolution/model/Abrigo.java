package br.com.fiap.globalsolution.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "global_abrigo")
public class Abrigo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAbrigo;
    @Enumerated(EnumType.STRING)
    private Bairro bairro;
    private String cep;
    private Integer qtdVagas;
    @OneToMany(mappedBy = "abrigo")
    private List<Voluntario> voluntarios;

    public Long getIdAbrigo() {
        return idAbrigo;
    }

    public void setIdAbrigo(Long idAbrigo) {
        this.idAbrigo = idAbrigo;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getQtdVagas() {
        return qtdVagas;
    }

    public void setQtdVagas(Integer qtdVagas) {
        this.qtdVagas = qtdVagas;
    }

    public List<Voluntario> getVoluntarios() {
        return voluntarios;
    }

    public void setVoluntarios(List<Voluntario> voluntarios) {
        this.voluntarios = voluntarios;
    }
}
