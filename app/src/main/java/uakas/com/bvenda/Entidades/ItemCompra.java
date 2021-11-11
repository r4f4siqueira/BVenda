package uakas.com.bvenda.Entidades;

import android.content.ContentValues;

import java.io.Serializable;

public class ItemCompra implements Serializable,EntidadeBanco {
    private Integer id;
    private Integer id_compra;
    private Integer id_produto;
    private Float quantidade;
    private Float valor;//isso aqui eh valor da unidade

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public ContentValues getDadosSalvar() {
        ContentValues dados = new ContentValues();
        dados.put("id",id);
        dados.put("id_compra",id_compra);
        dados.put("id_produto",id_produto);
        dados.put("quantidade",quantidade);
        dados.put("valor_un",valor);//lembrando que isso eh valor unitario

        return dados;
    }

    @Override
    public EntidadeBanco setDados(String[] dados) { // nao tem um jeito mais facil de fazer isso aqui nao quebrar quando um desses valores for nulo???
        ItemCompra ic = new ItemCompra();
        ic.setId(Integer.parseInt(dados[0]));
        ic.setId_compra(Integer.parseInt(dados[1]));
        ic.setId_produto(Integer.parseInt(dados[2]));
        ic.setQuantidade(Float.parseFloat(dados[3]));
        ic.setValor(Float.parseFloat(dados[4]));
        return ic;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_compra() {
        return id_compra;
    }

    public void setId_compra(Integer id_compra) {
        this.id_compra = id_compra;
    }

    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }

    public Float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "ItemCompra{" +
                "id=" + id +
                ", id_compra=" + id_compra +
                ", id_produto=" + id_produto +
                ", quantidade=" + quantidade +
                ", valor=" + valor +
                '}';
    }
}
