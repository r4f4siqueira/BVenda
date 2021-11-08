package uakas.com.bvenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import uakas.com.bvenda.Banco.BDDados;
import uakas.com.bvenda.Entidades.Pessoa;
import uakas.com.bvenda.Entidades.Produto;

public class produto extends AppCompatActivity {
    EditText  TextoNome;
    Switch  Switchativo;
    EditText Textovalor;
    EditText Textoqtd_estoque;
    TextView CodigoProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        TextoNome = findViewById(R.id.etProdutoNome);
        Switchativo = findViewById(R.id.swProdutoAtivo);
        Textovalor = findViewById(R.id.etProdutoValor);
        Textoqtd_estoque = findViewById(R.id.etProdutoQuantidade);
        CodigoProduto = findViewById(R.id.tvProdutoCodigo);

    }

    public void menul (View view){
        Intent it = new Intent(this, menuLateral.class);
        startActivityForResult(it,100);
    }

    public void salvarProduto(View view){
        Produto p = new Produto(); // cria uma nova classe
        p.setNome(TextoNome.getText().toString()); // preenche a classe com o que ta escrito
        p.setAtivo(Switchativo.getShowText());
        p.setValor(Float.parseFloat(Textovalor.getText().toString()));
        p.setQtd_estoque(Float.parseFloat(Textoqtd_estoque.getText().toString()));
        BDDados.Salvar(p,getApplicationContext()); //funciona se o metodo de salvar tiver rodando, tem q fazer isso em todas as telas
        onBackPressed();
    }

    public void removerProduto (View view){
        Produto p = new Produto();
        p.setId(Integer.parseInt(CodigoProduto.getText().toString()));
        try {
            BDDados.Remover(p,getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        onBackPressed();
    }

    public void cancelar(View view){
        onBackPressed();
    }
}