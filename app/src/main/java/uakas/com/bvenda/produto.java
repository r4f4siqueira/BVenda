package uakas.com.bvenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;

import uakas.com.bvenda.Banco.BDDados;
import uakas.com.bvenda.Entidades.Pessoa;
import uakas.com.bvenda.Entidades.Produto;

public class produto extends AppCompatActivity {
    EditText  TextoNome;
    Switch  Switchativo;
    EditText Textovalor;
    EditText Textoqtd_estoque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        TextoNome = findViewById(R.id.etProdutoNome);
        Switchativo = findViewById(R.id.swProdutoAtivo);
        Textovalor = findViewById(R.id.etProdutoValor);
        Textoqtd_estoque = findViewById(R.id.etProdutoQuantidade);
    }

    public void salvarProduto(){
        Produto p = new Produto(); // cria uma nova classe
        p.setNome(TextoNome.getText().toString()); // preenche a classe com o que ta escrito
        p.setAtivo(Switchativo.getShowText());
        p.setValor(Float.parseFloat(Textovalor.getText().toString()));//Temos que resolver isso aki
        p.setQtd_estoque(Float.parseFloat(Textoqtd_estoque.getText().toString()));//mesma coisa que o de cima
        BDDados.Salvar(p,getApplicationContext()); //funciona se o metodo de salvar tiver rodando, tem q fazer isso em todas as telas
    }
}