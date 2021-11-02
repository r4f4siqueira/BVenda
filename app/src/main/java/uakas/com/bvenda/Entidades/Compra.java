package uakas.com.bvenda.Entidades;

import java.io.Serializable;

public class Compra implements Serializable {
    private Integer codcompra;
    private Integer id_fornecedor;
    private String descricao;
    private Float valor;

    public Integer getCodcompra() {
        return codcompra;
    }

    public void setCodcompra(Integer codcompra) {
        this.codcompra = codcompra;
    }

    public Integer getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(Integer id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
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