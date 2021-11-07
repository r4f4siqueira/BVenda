package uakas.com.bvenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import uakas.com.bvenda.Banco.BDDados;
import uakas.com.bvenda.Entidades.Compra;
import uakas.com.bvenda.Entidades.Venda;

public class compra extends AppCompatActivity {


    TextView Textoid;
    EditText Textoid_fornecedor;
    EditText Textodescricao;
    TextView Textovalor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);
        Textoid = findViewById(R.id.tvCompraCod);
        Textoid_fornecedor = findViewById(R.id.etCompraFornecedor);
        Textodescricao = findViewById(R.id.etCompraDescricao);
        Textovalor = findViewById(R.id.etCompraProduto);

    }

    public void salvarCompra(View view){
        Compra c = new Compra(); // cria uma nova classe
        c.setId(Integer.parseInt(Textoid.getText().toString()));
        c.setDescricao(Textodescricao.getText().toString());
        c.setValor(Float.parseFloat(Textovalor.getText().toString()));
        c.setId_fornecedor(Integer.parseInt(Textoid_fornecedor.getText().toString()));
        //BDDados.Salvar(c,getApplicationContext()); //funciona se o metodo de salvar tiver rodando, tem q fazer isso em todas as telas
    }

    public void salvarItemCompra(View view){
        //salva um itemCompra pra dentro do banco e adiciona ele na listview
    }
}