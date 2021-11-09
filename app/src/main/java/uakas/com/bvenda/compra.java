package uakas.com.bvenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import uakas.com.bvenda.Banco.BDDados;
import uakas.com.bvenda.Entidades.Compra;
import uakas.com.bvenda.Entidades.Conta;
import uakas.com.bvenda.Entidades.ItemCompra;
import uakas.com.bvenda.Entidades.Pessoa;
import uakas.com.bvenda.Entidades.Produto;
import uakas.com.bvenda.Entidades.Venda;

public class compra extends AppCompatActivity {


    TextView Textoid;
    EditText Textoid_fornecedor;
    EditText Textodescricao;
    TextView Textovalor;
    TextView Textoquantidade;
    ListView ListaItens;
    private List<ItemCompra> dados;
    ArrayAdapter<String> adapter;
    EditText Textoid_produto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);
        Textoid = findViewById(R.id.tvCompraCod);
        Textoid_fornecedor = findViewById(R.id.etCompraFornecedor);
        Textodescricao = findViewById(R.id.etCompraDescricao);
        Textovalor = findViewById(R.id.tvCompraValorFinal);
        Textoquantidade = findViewById(R.id.etCompraQuantidade);
        ListaItens = findViewById(R.id.lvItensCompra);
        Textoid_produto = findViewById(R.id.etCompraProduto);
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

    public void salvarCompra(View view){
        Compra c = new Compra(); // cria uma nova classe
        //c.setId(Integer.parseInt(Textoid.getText().toString()));
        c.setDescricao(Textodescricao.getText().toString());
        c.setValor(Float.parseFloat(Textovalor.getText().toString()));
        c.setId_fornecedor(Integer.parseInt(Textoid_fornecedor.getText().toString()));
        BDDados.Salvar(c,getApplicationContext()); //funciona se o metodo de salvar tiver rodando, tem q fazer isso em todas as telas
        if (view != null) {
            Button btn = (Button) view;
            Intent it = new Intent(this, lista.class);
            it.putExtra("entidade", btn.getHint().toString());
            startActivityForResult(it, 205);
        } else {
            Textoid.setText(BDDados.getlastid("compra",new String[]{"id"},getApplicationContext())+"");
        }

    }

    private void Atualizar() {
        //dados = BDDados.Listar(new ItemCompra(), getApplicationContext(), null, "id_compra = ?", new String[]{"" + Textoid.getText().toString()});
        //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dados);
        //ListaItens.setAdapter(adapter);
    }

    public void salvarItemCompra(View view){
        if(Textoid_fornecedor.getText().toString().equals("") || Textodescricao.getText().toString().equals("")) {
            Toast.makeText(this, "preencha os campos da compra primeiro", Toast.LENGTH_SHORT).show();
        } else {
            if(Textoid.getText().toString().equals("CodCom")){
                salvarCompra(null);
            }
            ItemCompra ic = new ItemCompra();
            //ic.setValor(); pega do produto
            if (!Textoid.getText().toString().equals(""))
                ic.setId_compra(Integer.parseInt(Textoid.getText().toString()));
            if (!Textoid_produto.getText().toString().equals(""))
                ic.setId_produto(Integer.parseInt(Textoid_produto.getText().toString())); //pega do produto
            if (!Textoquantidade.getText().toString().equals(""))
                ic.setQuantidade(Float.parseFloat(Textoquantidade.getText().toString()));
            BDDados.Salvar(ic, getApplicationContext());

            Atualizar();
        }
    }

    public void removerCompra (View view){
        Compra c = new Compra();
        c.setId(Integer.parseInt(Textoid.getText().toString()));
        try {
            BDDados.Remover(c,getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        onBackPressed();
    }

    public void cancelar(View view){
        onBackPressed();
    }
}