package uakas.com.bvenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import uakas.com.bvenda.Banco.BDDados;
import uakas.com.bvenda.Banco.BDSetup;
import uakas.com.bvenda.Entidades.Compra;
import uakas.com.bvenda.Entidades.Pessoa;
import uakas.com.bvenda.Entidades.Produto;
import uakas.com.bvenda.Entidades.Venda;

public class lista extends AppCompatActivity {

    private ListView lista;
    private RecyclerView recyclerView;
    private List<Object> dados;
    ArrayAdapter<String> adapter;
    private Object classe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        lista = findViewById(R.id.lvObjeto);
        Atualizar();

    }

    private void Atualizar() {
        Intent it = getIntent();
        if (it.getStringExtra("entidade") != null) { // por algum motivo ele nao passa aqui
            switch (it.getStringExtra("entidade").toLowerCase(Locale.ROOT)) { // diferenciador pros botoes apertados, tem que ter coisa no "labelfor" do botao
                //nao sei se null funciona com campos
                case "cliente":
                    dados = BDDados.Listar(new Pessoa(), getApplicationContext(), null, "fornecedor = ?", new String[]{"" + 0});
                    //dados = new LinkedList<>();
                    classe = new pessoa();
                    //dados.add("pessoa");
                    break;
                case "produto":
                    dados = BDDados.Listar(new Produto(), getApplicationContext(), null, null, null);
                    //dados = new LinkedList<>();
                    classe = new produto();
                    //dados.add("produto");
                    break;
                case "fornecedor":
                    dados = BDDados.Listar(new Pessoa(), getApplicationContext(), null, "fornecedor = ?", new String[]{"" + 1});
                    //dados = new LinkedList<>();
                    classe = new pessoa();
                    //dados.add("fornecedor");
                    break;
                case "venda":
                    //dados = BDDados.Listar(new venda(), getApplicationContext(), null, null, null);
                    dados = new LinkedList<>();
                    classe = new venda();
                    dados.add("venda");
                    break;
                case "compra":
                    //dados = BDDados.Listar(new compra(), getApplicationContext(), null, null, null);
                    dados = new LinkedList<>();
                    classe = new compra();
                    dados.add("compra");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + getIntent().getStringExtra("entidade"));
            }
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dados);
            lista.setAdapter(adapter);
        } else {
            dados = new LinkedList<>();
            dados.add("nada");
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dados);
            lista.setAdapter(adapter);
        }
    }
    public void adicionar(View view){
        Intent it = new Intent(this,classe.getClass()); //cria a intent da tela da entidade que foi criada, pode dar merda pq tem classe com o msm nome
        startActivity(it);
        Atualizar();
    }


}