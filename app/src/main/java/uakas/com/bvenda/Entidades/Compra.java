package uakas.com.bvenda.Entidades;

import android.content.ContentValues;

import java.io.Serializable;

public class Compra implements Serializable,EntidadeBanco {
    private Integer id;
    private Integer id_fornecedor;
    private String descricao;
    private Float valor;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public ContentValues getDadosSalvar() {
        ContentValues dados = new ContentValues();
        dados.put("id",id);
        dados.put("id_fornecedor",id_fornecedor);
        dados.put("observacao",descricao);
        dados.put("valor",valor);

        return dados;
    }

    @Override
    public EntidadeBanco setDados(String[] dados) {
        Compra c = new Compra();

        c.setId(Integer.parseInt(dados[0]));
        c.setId_fornecedor(Integer.parseInt(dados[1]));
        c.setDescricao(dados[2]);
        c.setValor(Float.parseFloat(dados[3]));

        return c;
    }

    public void setId(Integer id) {
        this.id = id;
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

    //talvez mudar em vez do id de uma entidade colocar a entidade inteira dentro da classe igual fizemos no linebyte

    @Override
    public String toString() {
        return "id compra = " + id +
                " fornecedor = " + id_fornecedor +
                "\n descricao = " + descricao  +
                "\n valor = " + valor;
    }
}