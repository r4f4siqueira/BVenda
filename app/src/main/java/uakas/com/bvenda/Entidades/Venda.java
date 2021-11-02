package uakas.com.bvenda.Entidades;

import java.io.Serializable;

public class Venda implements Serializable {
    private Integer codvenda;
    private Integer id_cliente;
    private String descricao;
    private Float valor;

    public Integer getCodvenda() {
        return codvenda;
    }

    public void setCodvenda(Integer codvenda) {
        this.codvenda = codvenda;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}
