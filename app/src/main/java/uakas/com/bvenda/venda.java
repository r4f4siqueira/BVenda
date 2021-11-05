package uakas.com.bvenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import uakas.com.bvenda.Banco.BDDados;
import uakas.com.bvenda.Entidades.Produto;
import uakas.com.bvenda.Entidades.Venda;

public class venda extends AppCompatActivity {
    TextView Textoid;
    EditText Textoid_cliente;
    EditText Textodescricao;
    TextView Textovalor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda);
        Textoid = findViewById(R.id.tvVendaCod);
        Textoid_cliente = findViewById(R.id.etVendaCliente);
        Textodescricao = findViewById(R.id.etVendaDescricao);
        Textovalor = findViewById(R.id.etProdutoValor);
    }

    public void menul (View view){
        Intent it = new Intent(this, menuLateral.class);
        startActivityForResult(it,101);
    }

    public void listar (View view){
        Button btn = (Button) view;
        Intent it = new Intent(this, lista.class);
        it.putExtra("entidade",btn.getHint().toString());//por algu motivo isso nao funciona
        startActivity(it);
    }

    public void salvarVenda(){
        Venda v = new Venda(); // cria uma nova classe
        v.setId(Integer.parseInt(Textoid.getText().toString()));
        v.setDescricao(Textodescricao.getText().toString());
        v.setValor(Float.parseFloat(Textovalor.getText().toString()));
        v.setId_cliente(Integer.parseInt(Textoid_cliente.getText().toString()));
        BDDados.Salvar(v,getApplicationContext()); //funciona se o metodo de salvar tiver rodando, tem q fazer isso em todas as telas
    }

    public void salvarItemVenda(){
        //salva um itemvenda pra dentro do banco e adiciona ele na listview
    }
}