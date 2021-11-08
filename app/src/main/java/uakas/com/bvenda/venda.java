package uakas.com.bvenda;

import androidx.annotation.Nullable;
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
    EditText Textovalor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda);
        Textoid = findViewById(R.id.tvVendaCod);
        Textoid_cliente = findViewById(R.id.etVendaCliente);
        Textodescricao = findViewById(R.id.etVendaDescricao);
        Textovalor = findViewById(R.id.tvVendaValorFinal);
    }

    public void menul (View view){
        Intent it = new Intent(this, menuLateral.class);
        startActivityForResult(it,101);
    }

    public void listar (View view){
        Button btn = (Button) view;
        Intent it = new Intent(this, lista.class);
        it.putExtra("entidade",btn.getHint().toString());//por algu motivo isso nao funciona
        startActivityForResult(it,200);
    }

    public void salvarVenda(View view){
        Venda v = new Venda(); // cria uma nova classe
        //v.setId(Integer.parseInt(Textoid.getText().toString()));
        v.setDescricao(Textodescricao.getText().toString());
        v.setValor(Float.parseFloat(Textovalor.getText().toString()));
        v.setId_cliente(Integer.parseInt(Textoid_cliente.getText().toString()));
        BDDados.Salvar(v,getApplicationContext()); //funciona se o metodo de salvar tiver rodando, tem q fazer isso em todas as telas


        Button btn = (Button) view;
        Intent it = new Intent(this, lista.class);
        it.putExtra("entidade",btn.getHint().toString());
        startActivityForResult(it,205);
    }

    public void salvarItemVenda(View view){
        //salva um itemvenda pra dentro do banco e adiciona ele na listview
    }

    public void removerVenda (View view){
        Venda v = new Venda();
        v.setId(Integer.parseInt(Textoid.getText().toString()));
        try {
            BDDados.Remover(v,getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        onBackPressed();
    }

    public void cancelar(View view){
        onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 200){
            //pegar o objeto que foi mandado la da outra tela de algum jeito
        }
    }
}