package uakas.com.bvenda.Entidades;

import android.content.ContentValues;

import java.io.Serializable;

public class Venda implements Serializable,EntidadeBanco {
    private Integer id;
    private Integer id_cliente;
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
        dados.put("id_cliente",id_cliente);
        dados.put("descricao",descricao);
        dados.put("valor",valor);
        dados.put("concluido",concluido);

        return dados;
    }

    @Override
    public EntidadeBanco setDados(String[] dados) {
        Venda v = new Venda();

        if (dados[0] != null)
            v.setId(Integer.parseInt(dados[0]));
        if (dados[1] != null)
            v.setId_cliente(Integer.parseInt(dados[1]));
        if (dados[2] != null)
            v.setDescricao(dados[2]);
        if (dados[3] != null)
            v.setValor(Float.parseFloat(dados[3]));
        if (dados[4] != null)
            v.setConcluido(dados[4].equals("1")?true:false);

        return v;
    }

    public Boolean getConcluido() { return concluido; }

    public void setConcluido(Boolean concluido) { this.concluido = concluido; }

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
        return "Venda: " + id +
                "\nCliente: " + id_cliente +
                "\nDescricao: " + descricao +
                "\nValor: " + valor+
                "\nConcluido: "+concluido;
    }
}
