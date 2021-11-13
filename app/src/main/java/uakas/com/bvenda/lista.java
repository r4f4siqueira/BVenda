package uakas.com.bvenda;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import uakas.com.bvenda.Banco.BDDados;
import uakas.com.bvenda.Banco.BDSetup;
import uakas.com.bvenda.Entidades.Compra;
import uakas.com.bvenda.Entidades.Conta;
import uakas.com.bvenda.Entidades.EntidadeBanco;
import uakas.com.bvenda.Entidades.ItemCompra;
import uakas.com.bvenda.Entidades.ItemVenda;
import uakas.com.bvenda.Entidades.Pessoa;
import uakas.com.bvenda.Entidades.Produto;
import uakas.com.bvenda.Entidades.Venda;

public class lista extends AppCompatActivity {

    private ListView lista;
    private RecyclerView recyclerView;
    private List<Object> dados;
    ArrayAdapter<String> adapter;
    private Object classe;
    private EntidadeBanco entidade;
    private EntidadeBanco Item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        lista = findViewById(R.id.lvObjeto);
        Atualizar();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //aqui que vai pegar o objeto e mandar ele para a tela que chamou essa, colocando dentro do campo de texto la bonitinho
                // nao sei como fazer ainda, mas de ideia eu tive : super.getintent(), ou getparent().getintent() pra pegar a intent da tela que chamou essa
                // sem ideias de como mandar para lá o objeto
                EntidadeBanco eb = (EntidadeBanco) dados.get(i);
                Intent it = new Intent();
                it.putExtra("EB", (Serializable) eb);
                setResult(RESULT_OK,it);
                onBackPressed();
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //aqui dentro chama a tela de cadastro do objeto quando clica em cima dele
                Intent it = new Intent(getApplicationContext(),classe.getClass());
                it.putExtra("objeto", (Serializable) dados.get(i));
                startActivityForResult(it,357); // n sei pq quando atualiza uma entidade existente ela pula essa tela e volta pra anterior
                return false;
            }
        });
    }

    private void Atualizar() {
        Intent it = getIntent();
        if (it.getStringExtra("entidade") != null) { // por algum motivo ele nao passa aqui
            switch (it.getStringExtra("entidade").toLowerCase(Locale.ROOT)) { // diferenciador pros botoes apertados, tem que ter coisa no "hint" do botao
                //nao sei se null funciona com campos
                case "cliente":
                    dados = BDDados.Listar(new Pessoa(), getApplicationContext(), null, "fornecedor = ?", new String[]{"" + 0});
                    //dados = new LinkedList<>();
                    classe = new pessoa();
                    entidade = new Pessoa();
                    //dados.add("pessoa");
                    break;
                case "produto":
                    dados = BDDados.Listar(new Produto(), getApplicationContext(), null, null, null);
                    //dados = new LinkedList<>();
                    classe = new produto();
                    entidade = new Produto();
                    //dados.add("produto");
                    break;
                case "fornecedor":
                    dados = BDDados.Listar(new Pessoa(), getApplicationContext(), null, "fornecedor = ?", new String[]{"" + 1});
                    //dados = new LinkedList<>();
                    classe = new pessoa();
                    entidade = new Pessoa();
                    //dados.add("fornecedor");
                    break;
                case "venda":
                    dados = BDDados.Listar(new Venda(), getApplicationContext(), null, null, null);
                    //dados = new LinkedList<>();
                    classe = new venda();
                    entidade = new Venda();
                    Item = new ItemVenda();
                    //dados.add("venda");
                    break;
                case "compra":
                    dados = BDDados.Listar(new Compra(), getApplicationContext(), null, null, null);
                    //dados = new LinkedList<>();
                    classe = new compra();
                    entidade = new Compra();
                    Item = new ItemCompra();
                    //dados.add("compra");
                    break;
                case "conta":
                    dados = BDDados.Listar(new Conta(), getApplicationContext(), null, null, null);
                    //dados = new LinkedList<>();
                    classe = new contas();
                    entidade = new Conta();
                    //dados.add("compra");
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
        Atualizar();
        Intent it = new Intent(this,classe.getClass()); //cria a intent da tela da entidade que foi criada, pode dar merda pq tem classe com o msm nome
        startActivityForResult(it,347);
    }

    public void destruicao(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(lista.this);
        builder.setCancelable(true);
        builder.setTitle("Voce tem certeza?");
        builder.setMessage("isso irá apagar TODAS as entradas existentes dessa tabela no banco de dados, esse processo é irreversível");
        builder.setPositiveButton("sim",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BDDados.bombaNuclear(entidade,getApplicationContext());
                        //BDDados.bombaNuclear(new Venda(),getApplicationContext());
                        if (entidade.getClass().getSimpleName().equals("Venda") || entidade.getClass().getSimpleName().equals("Compra")){
                            BDDados.bombaNuclear(Item,getApplicationContext());
                           // BDDados.bombaNuclear(new ItemVenda(),getApplicationContext());
                            Toast.makeText(lista.this, "Itens removidos", Toast.LENGTH_SHORT).show();
                        }
                        Atualizar();
                    }
                });
        builder.setNegativeButton("nao",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Atualizar();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 347 && resultCode == RESULT_OK) { // salvou um objeto novo
            Atualizar();
            Toast.makeText(this, "Item salvo com sucesso", Toast.LENGTH_SHORT).show();
        }
        if (requestCode == 357 && resultCode == RESULT_OK) { // clicou no objeto para editar
            Atualizar();
            Toast.makeText(this, "Item Editado com sucesso", Toast.LENGTH_SHORT).show();
        }
    }
}