package uakas.com.bvenda.Entidades;

import android.content.ContentValues;

import java.io.Serializable;

public class Venda implements Serializable,EntidadeBanco {
    private Integer id;
    private Integer id_cliente;
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
        dados.put("id_cliente",id_cliente);
        dados.put("descricao",descricao);
        dados.put("valor",valor);

        return dados;
    }

    @Override
    public EntidadeBanco setDados(String[] dados) {
        Venda v = new Venda();

        v.setId(Integer.parseInt(dados[0]));
        v.setId_cliente(Integer.parseInt(dados[1]));
        v.setDescricao(dados[2]);
        v.setValor(Float.parseFloat(dados[3]));

        return v;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", id_cliente=" + id_cliente +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }
}
