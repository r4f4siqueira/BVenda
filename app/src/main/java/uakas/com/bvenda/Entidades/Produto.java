package uakas.com.bvenda.Entidades;

import android.content.ContentValues;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Produto implements Serializable,EntidadeBanco {
    private Integer id;
    private String nome;
    private Boolean ativo;
    private Float valor;
    private Float qtd_estoque;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public ContentValues getDadosSalvar() {

        ContentValues dados = new ContentValues();
        dados.put("id",id);
        dados.put("nome",nome);
        dados.put("ativo",ativo);
        dados.put("valor",valor);
        dados.put("qtd_estoque",qtd_estoque);

        return dados;
    }

    @Override
    public EntidadeBanco setDados(String[] dados) { //  isso DEVE seguir a ordem dos atributos das tabelas do banco no BDSetup
        Produto p = new Produto();
        //id = Integer.parseInt(dados[0]); //era assim
        p.setId(Integer.parseInt(dados[0])); // ficou assim
        p.setNome(dados[1]);
        p.setAtivo(dados[2].equals("1")?true:false);
        if (dados[3] != null){
            p.setValor(Float.parseFloat(dados[3]));
        }
        p.setQtd_estoque(Float.parseFloat(dados[4]));
        return p;
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
                ", qtd_estoque=" + qtd_estoque;
    }
}
