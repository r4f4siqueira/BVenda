package uakas.com.bvenda.Entidades;

import android.content.ContentValues;

import java.io.Serializable;

public class Pessoa implements Serializable, EntidadeBanco {
    private Integer id;
    private String nome;
    private String celular;
    private String email;
    private String observacao;
    private Boolean fornecedor;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public ContentValues getDadosSalvar() {// isso DEVE seguir a ordem dos atributos das tabelas do banco no BDSetup

        ContentValues dados = new ContentValues();
        dados.put("id",id);
        dados.put("nome",nome);
        dados.put("celular",celular);
        dados.put("email",email);
        dados.put("observacao",observacao);
        dados.put("fornecedor",fornecedor);

        return dados;
    }

    @Override
    public EntidadeBanco setDados(String[] dados) {
        Pessoa p = new Pessoa();

        if (dados[0] != null)
            p.setId(Integer.parseInt(dados[0]));
        if (dados[1] != null)
            p.setNome(dados[1]);
        if (dados[2] != null)
            p.setCelular(dados[2]);
        if (dados[3] != null)
            p.setEmail(dados[3]);
        if (dados[4] != null)
            p.setObservacao(dados[4]);
        if (dados[5] != null)
            p.setFornecedor(dados[5].equals("1")?true:false);

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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Boolean getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Boolean fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        return  id +" - "+nome+
                "\nCelular: " +celular+
                "\nEmail: "+email+
                "\nObservacao: " + observacao +
                "\nFornecedor: " + fornecedor;
    }
}
