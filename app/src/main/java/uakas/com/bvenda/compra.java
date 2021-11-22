package uakas.com.bvenda;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import uakas.com.bvenda.Banco.BDDados;
import uakas.com.bvenda.Entidades.Compra;
import uakas.com.bvenda.Entidades.Conta;
import uakas.com.bvenda.Entidades.EntidadeBanco;
import uakas.com.bvenda.Entidades.ItemCompra;
import uakas.com.bvenda.Entidades.Pessoa;
import uakas.com.bvenda.Entidades.Produto;
import uakas.com.bvenda.Entidades.Venda;

public class compra extends AppCompatActivity {


    private TextView Textoid;
    private EditText Textoid_fornecedor;
    private EditText Textodescricao;
    private TextView Textovalor;
    private EditText Textoquantidade;
    private ListView ListaItens;
    private List<ItemCompra> dados;
    private ArrayAdapter<String> adapter;
    private EditText Textoid_produto;
    private Compra c;
    private ItemCompra ic;
    private Produto produto;
    private Pessoa pessoa;
    private Switch switchConcluido;
    private Button BotaoSalvar;
    private Button BotaoSalvarItem;
    private Button BotaoPesquisarFornecedor;
    private Button BotaoPesquisarProduto;


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
        switchConcluido = findViewById(R.id.swCompra);
        //botoes
        BotaoSalvar = findViewById(R.id.btCompraGerar);
        BotaoSalvarItem = findViewById(R.id.btCompraAddItem);
        BotaoPesquisarFornecedor = findViewById(R.id.btCompraPesquisaFornecedor);
        BotaoPesquisarProduto = findViewById(R.id.btCompraPesquisaProduto);
        // se clicou pra editar
        Intent it = getIntent();
        if(it.getSerializableExtra("objeto")!=null){ // verifica se foi passado algum objeto para editar
            c = (Compra) it.getSerializableExtra("objeto");
            List<EntidadeBanco> lista; // feito pra nao dar pau na hora de editar
            if (c.getId() != null)
                Textoid.setText(c.getId()+"");
                Atualizar();
            if (c.getDescricao() != null)
                Textodescricao.setText(c.getDescricao()+"");
            if (c.getId_fornecedor() != null) {
                Textoid_fornecedor.setText(c.getId_fornecedor() + "");
                lista = BDDados.Listar(new Pessoa(), getApplicationContext(), null, "id = ?", new String[]{Textoid_fornecedor.getText().toString()});// muito cuidado usando essa funcao, qualquer coisa no campos quebra ela
                if (lista.get(0) != null){ // protecao para caso nao exista mais o fornecedor com esse ID
                    pessoa = (Pessoa) lista.get(0);
                }
            }
            if (c.getValor() != null)
                Textovalor.setText(c.getValor()+"");
            if (c.getConcluido() !=null){
                switchConcluido.setChecked(c.getConcluido());
                if(c.getConcluido()){// verifica se a compra está concluida
                    //desativando botoes
                    BotaoSalvar.setClickable(false);
                    BotaoSalvarItem.setClickable(false);
                    BotaoPesquisarProduto.setClickable(false);
                    BotaoPesquisarFornecedor.setClickable(false);

                    //desativando campos de texto
                    Textoid_fornecedor.setFocusable(false);
                    Textoid_fornecedor.setClickable(false);

                    Textodescricao.setFocusable(false);
                    Textodescricao.setClickable(false);

                    Textoid_produto.setFocusable(false);
                    Textoid_produto.setClickable(false);

                    Textoquantidade.setFocusable(false);
                    Textoquantidade.setClickable(false);

                    //desativando interacao com a lista de itens
                    ListaItens.setClickable(false);
                    ListaItens.setEnabled(false);

                    switchConcluido.setClickable(true);
                }
            }
        } else {
            Reset();
            c = new Compra();
        }

        ListaItens.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    BDDados.Remover(dados.get(i),getApplicationContext());
                    Toast.makeText(compra.this, "Item removido!", Toast.LENGTH_SHORT).show();
                    Atualizar();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

        switchConcluido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!switchConcluido.isChecked()) {
                    Reset();
                }
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
        it.putExtra("entidade",btn.getHint().toString());
        startActivityForResult(it,222); // 222 é o codigo do retorno de entidade para uma tela, fazer isso em todos os outros listar
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 222){
            //p = (Produto) data.getSerializableExtra("eb"); // tem q ser generico

            EntidadeBanco eb = (EntidadeBanco) data.getSerializableExtra("EB");
            switch (eb.getClass().getSimpleName().toLowerCase()){ // garantindo que vai ser minúsculo os nomes
                case "compra":
                    c = (Compra) data.getSerializableExtra("EB");
                    if (c.getId() != null)
                        Textoid.setText(c.getId()+"");
                    Atualizar();
                    if (c.getDescricao() != null)
                        Textodescricao.setText(c.getDescricao()+"");
                    if (c.getId_fornecedor() != null)
                        Textoid_fornecedor.setText(c.getId_fornecedor()+"");
                    if (c.getValor() != null)
                        Textovalor.setText(c.getValor()+"");
                    if (c.getConcluido()!=null) {
                        switchConcluido.setChecked(c.getConcluido());
                        if(c.getConcluido()) {// verifica se a compra está concluida
                            //desativando botoes
                            BotaoSalvar.setClickable(false);
                            BotaoSalvarItem.setClickable(false);
                            BotaoPesquisarProduto.setClickable(false);
                            BotaoPesquisarFornecedor.setClickable(false);

                            //desativando campos de texto
                            Textoid_fornecedor.setFocusable(false);
                            Textoid_fornecedor.setClickable(false);

                            Textodescricao.setFocusable(false);
                            Textodescricao.setClickable(false);

                            Textoid_produto.setFocusable(false);
                            Textoid_produto.setClickable(false);

                            Textoquantidade.setFocusable(false);
                            Textoquantidade.setClickable(false);

                            //desativando interacao com a lista de itens
                            ListaItens.setClickable(false);
                            ListaItens.setEnabled(false);

                            switchConcluido.setClickable(true);
                        }
                    }
                    List<EntidadeBanco> lista = BDDados.Listar(new Pessoa(), getApplicationContext(), null, "id = ?", new String[]{Textoid_fornecedor.getText().toString()});// muito cuidado usando essa funcao, qualquer coisa no campos quebra ela
                    if (lista.get(0) != null){ // protecao para caso nao exista mais o fornecedor com esse ID
                        pessoa = (Pessoa) lista.get(0);
                    }
                    Atualizar();
                    break;
                case "pessoa":
                    pessoa = (Pessoa) data.getSerializableExtra("EB");
                    if (!pessoa.getNome().equals("") && pessoa.getId() != null){
                        Textoid_fornecedor.setText(pessoa.getId() + " - " +pessoa.getNome());
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


    public void salvarCompra(View view){
        if(!Textoid.getText().toString().equals("CodCom")) {
            c.setId(Integer.parseInt(Textoid.getText().toString()));
        }
        String desc = Textodescricao.getText().toString();
        if (desc != null)
            c.setDescricao(desc);
        c.setValor(Float.parseFloat(Textovalor.getText().toString()));
        c.setConcluido(true);
        if (!Textoid_fornecedor.getText().toString().equals("") && pessoa != null) {
            c.setId_fornecedor(pessoa.getId()); //pega da pessoa
        } else {
            Toast.makeText(this, "Aviso! salvando pessoa inexistente!", Toast.LENGTH_SHORT).show();
        }
        //c.setId_fornecedor(Integer.parseInt(Textoid_fornecedor.getText().toString()));
        BDDados.Salvar(c,getApplicationContext()); //funciona se o metodo de salvar tiver rodando, tem q fazer isso em todas as telas
        if (view != null) {
            /*
            Button btn = (Button) view;
            Intent it = new Intent(this, lista.class);
            it.putExtra("entidade", btn.getHint().toString());
            startActivityForResult(it, 205);
             */
            //setResult(RESULT_OK);
            //onBackPressed();
            Reset();
            Toast.makeText(this, "Compra salva com sucesso!!!", Toast.LENGTH_SHORT).show();
        } else {
            Textoid.setText(BDDados.getlastid("compra",new String[]{"id"},getApplicationContext())+"");
        }

    }

    private void Atualizar() {
        dados = BDDados.Listar(new ItemCompra(), getApplicationContext(), null, "id_compra = ?", new String[]{"" + Textoid.getText().toString()});
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dados);
        ListaItens.setAdapter(adapter);
        Float soma = 0f;
        for (int i = 0;i<dados.size();i++){
            soma += dados.get(i).getValor()*dados.get(i).getQuantidade();
        }
        Textovalor.setText(soma+"");
    }

    public void salvarItemCompra(View view){
        if(Textoid_fornecedor.getText().toString().equals("") || Textodescricao.getText().toString().equals("")) {
            Toast.makeText(this, "preencha os campos da compra primeiro", Toast.LENGTH_SHORT).show();
        } else {
            if(Textoid.getText().toString().equals("CodCom")){ // só salva a compra se ela nao existir no banco, ela nao existe quando nao tem código
                salvarCompra(null);
            }
            ic = new ItemCompra();

            if (!Textoid.getText().toString().equals(""))
                ic.setId_compra(Integer.parseInt(Textoid.getText().toString()));
            if (!Textoquantidade.getText().toString().equals(""))
                ic.setQuantidade(Float.parseFloat(Textoquantidade.getText().toString()));
            if (!Textoid_produto.getText().toString().equals("") && produto != null) {
                ic.setId_produto(produto.getId()); //pega do produto
                ic.setValor(produto.getValor()); //pega do produto, 0 é placeholder
            } else {
                Toast.makeText(this, "Tentando salvar produto inexistente", Toast.LENGTH_SHORT).show();
            }

            BDDados.Salvar(ic, getApplicationContext());

            Atualizar();
        }
    }

    private void Reset (){ // reseta a tela para uma nova venda/compra
        //limpando a lista de itens
        List listavazia = new LinkedList();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listavazia);
        ListaItens.setAdapter(adapter);

        //limpando as entidades
        c = new Compra();
        ic = null;
        produto = null;
        pessoa = null;

        //limpando os campos
        Textoid.setText("CodCom");
        Textoid_fornecedor.setText("");
        Textodescricao.setText("");
        Textoid_produto.setText("");
        Textoquantidade.setText("");
        Textovalor.setText("0.00");
        switchConcluido.setChecked(false);

        //reativando botoes
        BotaoSalvar.setClickable(true);
        BotaoSalvarItem.setClickable(true);
        BotaoPesquisarProduto.setClickable(true);
        BotaoPesquisarFornecedor.setClickable(true);

        //reativando campos de texto
        Textoid_fornecedor.setFocusable(true);
        Textoid_fornecedor.setClickable(true);
        Textoid_fornecedor.setEnabled(true);

        Textodescricao.setFocusable(true);
        Textodescricao.setClickable(true);
        Textodescricao.setEnabled(true);

        Textoid_produto.setFocusable(true);
        Textoid_produto.setClickable(true);
        Textoid_produto.setEnabled(true);


        Textoquantidade.setFocusable(true);
        Textoquantidade.setClickable(true);
        Textoquantidade.setEnabled(true);

        //reativando interacao com a lista de itens
        ListaItens.setClickable(true);
        ListaItens.setEnabled(true);

        switchConcluido.setClickable(false);
    }

    public void removerCompra (View view){
        Compra c = new Compra();
        if(!Textoid.getText().toString().equals("CodCom")){
            c.setId(Integer.parseInt(Textoid.getText().toString()));
            try {
                for (int i = 0; i < dados.size(); i++) {
                    BDDados.Remover(dados.get(i),getApplicationContext());
                }
                BDDados.Remover(c,getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }
            onBackPressed();
            setResult(RESULT_CANCELED);
        } else {
            Toast.makeText(this, "impossivel deletar compra nao salva", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelar(View view){
        setResult(RESULT_CANCELED);
        onBackPressed();
    }
}