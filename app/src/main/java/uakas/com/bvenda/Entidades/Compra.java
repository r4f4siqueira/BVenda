package uakas.com.bvenda.Entidades;

import android.content.ContentValues;

import java.io.Serializable;

public class Compra implements Serializable,EntidadeBanco {
    private Integer id;
    private Integer id_fornecedor;
    private String descricao;
    private Float valor;
    private Boolean concluido;

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
        dados.put("concluido",concluido);

        return dados;
    }

    @Override
    public EntidadeBanco setDados(String[] dados) {
        Compra c = new Compra();

        if (dados[0] != null)
            c.setId(Integer.parseInt(dados[0]));
        if (dados[1] != null)
            c.setId_fornecedor(Integer.parseInt(dados[1]));
        if (dados[2] != null)
            c.setDescricao(dados[2]);
        if (dados[3] != null)
            c.setValor(Float.parseFloat(dados[3]));
        if (dados[4] != null)
            c.setConcluido(dados[4].equals("1")?true:false);

        return c;
    }

    public Boolean getConcluido() { return concluido; }

    public void setConcluido(Boolean concluido) { this.concluido = concluido; }

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
        return "Compra: " + id +
                "\nFornecedor: " + id_fornecedor +
                "\nDescricao: " + descricao  +
                "\nValor: " + valor+
                "\nConcluido: "+concluido;
    }
}