package uakas.com.bvenda.Entidades;

import java.io.Serializable;

public class Produto implements Serializable {
    private Integer id;
    private String nome;
    private Boolean ativo;
    private Float valor;
    private Float qtd_estoque;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Float getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(Float qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", ativo=" + ativo +
                ", valor=" + valor +
                ", qtd_estoque=" + qtd_estoque +
                '}';
    }
}
