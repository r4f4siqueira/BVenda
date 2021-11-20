package uakas.com.bvenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import uakas.com.bvenda.Banco.BDDados;
import uakas.com.bvenda.Entidades.Pessoa;
import uakas.com.bvenda.Entidades.Produto;

public class produto extends AppCompatActivity {
    private EditText  TextoNome;
    private Switch  Switchativo;
    private EditText Textovalor;
    private EditText Textoqtd_estoque;
    private TextView TextoId;
    private Produto p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        TextoNome = findViewById(R.id.etProdutoNome);
        Switchativo = findViewById(R.id.swProdutoAtivo);
        Textovalor = findViewById(R.id.etProdutoValor);
        Textoqtd_estoque = findViewById(R.id.etProdutoQuantidade);
        TextoId = findViewById(R.id.tvProdutoCodigo);

        Intent it = getIntent();
        if(it.getSerializableExtra("objeto")!=null){ // verifica se foi passado algum objeto para editar
            p = (Produto) it.getSerializableExtra("objeto");
            if (p.getId() != null)
                TextoId.setText(p.getId()+"");
            if (p.getNome() != null)
                TextoNome.setText(p.getNome()+"");
            if (p.getValor() != null)
                Textovalor.setText(p.getValor()+"");
            if (p.getQtd_estoque() != null)
                Textoqtd_estoque.setText(p.getQtd_estoque() +"");
            if(p.getAtivo() != null){
                Switchativo.setChecked(p.getAtivo());
            }
        } else {
            p = new Produto();
        }
    }

    public void menul (View view){
        Intent it = new Intent(this, menuLateral.class);
        startActivityForResult(it,100);
    }

    public void salvarProduto(View view){
        //Produto p = new Produto(); // cria uma nova classe
        if(!TextoId.getText().toString().equals("cod")) { // se nao for igual a o que vem escrito por padrao, nesse caso "cod", seta o id
            p.setId(Integer.parseInt(TextoId.getText().toString()));
        }
        p.setNome(TextoNome.getText().toString()); // preenche a classe com o que ta escrito
        p.setAtivo(Switchativo.isChecked());
        p.setValor(Float.parseFloat(Textovalor.getText().toString()));
        p.setQtd_estoque(Float.parseFloat(Textoqtd_estoque.getText().toString()));

        BDDados.Salvar(p,getApplicationContext()); //funciona se o metodo de salvar tiver rodando, tem q fazer isso em todas as telas

        setResult(RESULT_OK);
        onBackPressed();
    }

    public void removerProduto (View view){
        if(!TextoId.getText().toString().equals("cod")) {
            Produto p = new Produto();
            p.setId(Integer.parseInt(TextoId.getText().toString()));
            try {
                BDDados.Remover(p, getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }
            setResult(RESULT_CANCELED);
            onBackPressed();
        } else {
            Toast.makeText(this, "Impossivel salvar produto nao salvo", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelar(View view){
        setResult(RESULT_CANCELED );
        onBackPressed();
    }
}