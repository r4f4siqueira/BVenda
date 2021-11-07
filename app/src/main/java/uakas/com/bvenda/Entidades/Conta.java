package uakas.com.bvenda.Entidades;

import android.content.ContentValues;

import java.io.Serializable;

public class Conta implements Serializable,EntidadeBanco {
    private Integer id;
    private Integer id_venda;
    private Integer id_compra;
    private Integer id_cliente;
    private Float valor;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public ContentValues getDadosSalvar() {
        ContentValues dados = new ContentValues();
        dados.put("id",id);
        dados.put("id_venda",id_venda);
        dados.put("id_compra",id_compra);
        dados.put("id_cliente",id_cliente);
        dados.put("valor",valor);

        return dados;
    }

    @Override
    public EntidadeBanco setDados(String[] dados) {
        id = Integer.parseInt(dados[0]);
        id_venda = Integer.parseInt(dados[1]);
        id_compra= Integer.parseInt(dados[2]);
        id_cliente =Integer.parseInt(dados[3]);
        valor = Float.parseFloat(dados[4]);

        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_venda() {
        return id_venda;
    }

    public void setId_venda(Integer id_venda) {
        this.id_venda = id_venda;
    }

    public Integer getId_compra() {
        return id_compra;
    }

    public void setId_compra(Integer id_compra) {
        this.id_compra = id_compra;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", id_venda=" + id_venda +
                ", id_compra=" + id_compra +
                ", id_cliente=" + id_cliente +
                ", valor=" + valor +
                '}';
    }
}
