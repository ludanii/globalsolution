package br.com.fiap.globalsolution.model;

import jakarta.persistence.*;

@Entity
@Table(name = "global_voluntario")
public class Voluntario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVoluntario;
    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;
    @Enumerated(EnumType.STRING)
    private Funcao funcao;
    @ManyToOne
    @JoinColumn(name = "id_abrigo")
    private Abrigo abrigo;

    public Long getIdVoluntario() {
        return idVoluntario;
    }

    public void setIdVoluntario(Long idVoluntario) {
        this.idVoluntario = idVoluntario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public Abrigo getAbrigo() {
        return abrigo;
    }

    public void setAbrigo(Abrigo abrigo) {
        this.abrigo = abrigo;
    }
}