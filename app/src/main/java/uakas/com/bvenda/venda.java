package uakas.com.bvenda;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import uakas.com.bvenda.Banco.BDDados;
import uakas.com.bvenda.Entidades.Compra;
import uakas.com.bvenda.Entidades.EntidadeBanco;
import uakas.com.bvenda.Entidades.ItemCompra;
import uakas.com.bvenda.Entidades.ItemVenda;
import uakas.com.bvenda.Entidades.Pessoa;
import uakas.com.bvenda.Entidades.Produto;
import uakas.com.bvenda.Entidades.Venda;

public class venda extends AppCompatActivity {
    private TextView Textoid;
    private EditText Textoid_cliente;
    private EditText Textodescricao;
    private EditText Textovalor;
    private TextView Textoquantidade;
    private ListView ListaItens;
    private List<ItemVenda> dados;
    private ArrayAdapter<String> adapter;
    private EditText Textoid_produto;
    private Venda v;
    private ItemVenda iv;
    private Produto produto;
    private Pessoa pessoa;
    private Switch switchConcluido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda);
        Textoid = findViewById(R.id.tvVendaCod);
        Textoid_cliente = findViewById(R.id.etVendaCliente);
        Textodescricao = findViewById(R.id.etVendaDescricao);
        Textovalor = findViewById(R.id.tvVendaValorFinal);
        Textoquantidade = findViewById(R.id.etVendaQuantidade);
        ListaItens = findViewById(R.id.lvItensVenda);
        Textoid_produto = findViewById(R.id.etVendaProduto);
        switchConcluido = findViewById(R.id.swVenda);

        Intent it = getIntent();
        if(it.getSerializableExtra("objeto")!=null){
            v = (Venda) it.getSerializableExtra("objeto");
            List<EntidadeBanco> lista; // feito pra nao dar pau na hora de editar
            if ( v.getId() != null)
                Textoid.setText(v.getId()+"");
            Atualizar();
            if (v.getDescricao() != null)
                Textodescricao.setText(v.getDescricao()+"");
            if (v.getId_cliente() != null) {
                Textoid_cliente.setText(v.getId_cliente() + "");
                lista = BDDados.Listar(new Pessoa(), getApplicationContext(), null, "id = ?", new String[]{Textoid_cliente.getText().toString()});// muito cuidado usando essa funcao, qualquer coisa no campos quebra ela
                if (lista.get(0) != null){ // protecao para caso nao exista mais o cliente com esse ID
                    pessoa = (Pessoa) lista.get(0);
                } else {
                    Toast.makeText(this, "pessoa nao vinculada", Toast.LENGTH_SHORT).show();
                }
            }
            if (v.getValor() != null)
                Textovalor.setText(v.getValor()+"");
            if (v.getConcluido()!=null)
                switchConcluido.setChecked(v.getConcluido());
        } else {
            v = new Venda();
        }

        ListaItens.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    BDDados.Remover(dados.get(i),getApplicationContext());
                    Toast.makeText(venda.this, "Item removido!", Toast.LENGTH_SHORT).show();
                    Atualizar();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
    }

    public void menul (View view){
        Intent it = new Intent(this, menuLateral.class);
        startActivityForResult(it,101);
    }

    public void listar (View view){
        Button btn = (Button) view;
        Intent it = new Intent(this, lista.class);
        it.putExtra("entidade",btn.getHint().toString());//por algu motivo isso nao funciona
        startActivityForResult(it,222);
    }

    public void salvarVenda(View view){
        if(!Textoid.getText().toString().equals("CodVenda")) {
            v.setId(Integer.parseInt(Textoid.getText().toString()));
        }
        v.setDescricao(Textodescricao.getText().toString());
        v.setValor(Float.parseFloat(Textovalor.getText().toString()));
        v.setConcluido(switchConcluido.isChecked());
        if (!Textoid_cliente.getText().toString().equals("") && pessoa != null) {
            v.setId_cliente(pessoa.getId()); //pega da pessoa
        } else {
            Toast.makeText(this, "Aviso! salvando pessoa inexistente!", Toast.LENGTH_SHORT).show();
        }
        BDDados.Salvar(v,getApplicationContext()); //funciona se o metodo de salvar tiver rodando, tem q fazer isso em todas as telas
        if (view != null) {
            Reset();
            Toast.makeText(this, "Venda salva com sucesso!!!", Toast.LENGTH_SHORT).show();
        } else {
            Textoid.setText(BDDados.getlastid("venda",new String[]{"id"},getApplicationContext())+"");
        }
    }

    private void Atualizar() {
        dados = BDDados.Listar(new ItemVenda(), getApplicationContext(), null, "id_venda = ?", new String[]{"" + Textoid.getText().toString()});
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dados);
        ListaItens.setAdapter(adapter);
    }

    public void salvarItemVenda(View view){
        if(Textoid_cliente.getText().toString().equals("") || Textodescricao.getText().toString().equals("")) {
            Toast.makeText(this, "preencha os campos da Venda primeiro", Toast.LENGTH_SHORT).show();
        } else {
            if(Textoid.getText().toString().equals("CodVenda")){ // só salva a venda se ela nao existir no banco, ela nao existe quando nao tem código
                salvarVenda(null);
            }
            iv = new ItemVenda();

            if (!Textoid.getText().toString().equals(""))
                iv.setId_venda(Integer.parseInt(Textoid.getText().toString()));
            if (!Textoquantidade.getText().toString().equals(""))
                iv.setQuantidade(Float.parseFloat(Textoquantidade.getText().toString()));
            if (!Textoid_produto.getText().toString().equals("") && produto != null) {
                iv.setId_produto(produto.getId()); //pega do produto
                iv.setValor_un(produto.getValor()); //pega do produto, 0 é placeholder
                BDDados.Salvar(iv, getApplicationContext());
            } else {
                Toast.makeText(this, "Tentando salvar produto inexistente", Toast.LENGTH_SHORT).show();
            }


            Atualizar();
        }
    }

    private void Reset (){ // reseta a tela para uma nova venda/compra
        //limpando a lista de itens
        List listavazia = new LinkedList();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listavazia);
        ListaItens.setAdapter(adapter);

        //limpando as entidades
        v = null;
        iv = null;
        produto = null;
        pessoa = null;

        //limpando os campos
        Textoid.setText("CodVenda");
        Textoid_cliente.setText("");
        Textodescricao.setText("");
        Textoid_produto.setText("");
        Textoquantidade.setText("");
        Textovalor.setText("0.00");
        switchConcluido.setChecked(false);
    }
    public void removerVenda (View view){
        Venda v = new Venda();
        if(!Textoid.getText().toString().equals("CodVenda")){
            v.setId(Integer.parseInt(Textoid.getText().toString()));
            try {
                for (int i = 0; i < dados.size(); i++) {
                    BDDados.Remover(dados.get(i),getApplicationContext());
                }
                BDDados.Remover(v,getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }
            onBackPressed();
            setResult(RESULT_CANCELED);
        } else {
            Toast.makeText(this, "impossivel deletar venda nao salva", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelar(View view){
        setResult(RESULT_CANCELED);
        onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 222){
            //pegar o objeto que foi mandado la da outra tela de algum jeito
            EntidadeBanco eb = (EntidadeBanco) data.getSerializableExtra("EB");
            switch (eb.getClass().getSimpleName().toLowerCase()){ // garantindo que vai ser minúsculo os nomes
                case "venda":
                    v = (Venda) data.getSerializableExtra("EB");
                    if (v.getId() != null)
                        Textoid.setText(v.getId()+"");
                    Atualizar();
                    if (v.getDescricao() != null)
                        Textodescricao.setText(v.getDescricao()+"");
                    if (v.getId_cliente() != null)
                        Textoid_cliente.setText(v.getId_cliente()+"");
                    if (v.getValor() != null)
                        Textovalor.setText(v.getValor()+"");
                    if (v.getConcluido()!=null)
                        switchConcluido.setChecked(v.getConcluido());
                    List<EntidadeBanco> lista = BDDados.Listar(new Pessoa(), getApplicationContext(), null, "id = ?", new String[]{Textoid_cliente.getText().toString()});// muito cuidado usando essa funcao, qualquer coisa no campos quebra ela
                    if (lista.get(0) != null){ // protecao para caso nao exista mais o cliente com esse ID
                        pessoa = (Pessoa) lista.get(0);
                    } else {
                        Toast.makeText(this, "pessoa nao vinculada", Toast.LENGTH_SHORT).show();
                    }
                    Atualizar();
                    break;
                case "pessoa":
                    pessoa = (Pessoa) data.getSerializableExtra("EB");
                    if (!pessoa.getNome().equals("") && pessoa.getId() != null){
                        Textoid_cliente.setText(pessoa.getId() + " - " +pessoa.getNome());
                    } else {
                        Toast.makeText(this, "Erro na selecao da pessoa", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "produto":
                    produto = (Produto) data.getSerializableExtra("EB");
                    if (!produto.getNome().equals("") && produto.getId() != null){
                        Textoid_produto.setText(produto.getId() + " - " + produto.getNome());
                    } else {
                        Toast.makeText(this, "Erro na selecao do produto", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
}