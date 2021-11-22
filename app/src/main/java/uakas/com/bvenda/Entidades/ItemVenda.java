package uakas.com.bvenda.Entidades;

import android.content.ContentValues;

import java.io.Serializable;

public class ItemVenda implements Serializable,EntidadeBanco {
    private Integer id;
    private Integer id_venda;
    private Integer id_produto;
    private Float quantidade;
    private Float valor_un;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public ContentValues getDadosSalvar() {
        ContentValues dados = new ContentValues();
        dados.put("id",id);
        dados.put("id_venda",id_venda);
        dados.put("id_produto",id_produto);
        dados.put("quantidade",quantidade);
        dados.put("valor_un",valor_un);

        return dados;
    }

    @Override
    public EntidadeBanco setDados(String[] dados) {
        ItemVenda iv = new ItemVenda();

        if (dados[0] != null)
            iv.setId(Integer.parseInt(dados[0]));
        if (dados[1] != null)
            iv.setId_venda(Integer.parseInt(dados[1]));
        if (dados[2] != null)
            iv.setId_produto(Integer.parseInt(dados[2]));
        if (dados[3] != null)
            iv.setQuantidade(Float.parseFloat(dados[3]));
        if (dados[4] != null)
            iv.setValor_un(Float.parseFloat(dados[4]));

        return iv;
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

    public Float getValor_un() {
        return valor_un;
    }

    public void setValor_un(Float valor_un) {
        this.valor_un = valor_un;
    }

    @Override
    public String toString() {
        return "ItemVenda{" +
                "id=" + id +
                ", id_venda=" + id_venda +
                ", id_produto=" + id_produto +
                ", quantidade=" + quantidade +
                ", valor_un=" + valor_un +
                '}';
    }
}
