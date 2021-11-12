package uakas.com.bvenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import uakas.com.bvenda.Banco.BDDados;
import uakas.com.bvenda.Entidades.Compra;
import uakas.com.bvenda.Entidades.EntidadeBanco;
import uakas.com.bvenda.Entidades.Pessoa;

public class pessoa extends AppCompatActivity {
    private EditText TextoNome; //colocar o tipo do campo e o nome seguindo esse padrao
    private EditText TextoCelular;
    private EditText TextoEmail;
    private EditText TextoObservacao;
    private TextView Textoid;
    private Switch SwitchFornecedor;
    private Pessoa p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);
        TextoNome = findViewById(R.id.etPesoaNome); // vincula com o id que voce colocou no design seguindo esse padrao aqui
        TextoCelular = findViewById(R.id.etPessoaCelular);
        TextoEmail = findViewById(R.id.etPessoaEmail);
        TextoObservacao = findViewById(R.id.etPessoaObservacao);
        SwitchFornecedor = findViewById(R.id.swPessoaFornecedor);
        Textoid = findViewById(R.id.tvPessoaCod);

        Intent it = getIntent();
        if(it.getSerializableExtra("objeto")!=null){ // verifica se foi passado algum objeto para editar
            p = (Pessoa) it.getSerializableExtra("objeto");
            if (p.getId() != null)
                Textoid.setText(p.getId()+"");
            if (p.getNome() != null)
                TextoNome.setText(p.getNome()+"");
            if (p.getCelular() != null)
                TextoCelular.setText(p.getCelular()+"");
            if (p.getEmail() != null)
                TextoEmail.setText(p.getEmail() +"");
            if(p.getFornecedor() != null){
                SwitchFornecedor.setChecked(p.getFornecedor());
            }
            if(p.getObservacao() != null){
                TextoObservacao.setText(p.getObservacao()+"");
            }
        } else {
            p = new Pessoa();
        }
    }

    public void salvarpessoa(View view){
        //Pessoa p = new Pessoa(); // cria uma nova classe
        if(!Textoid.getText().toString().equals("cod")) { // se nao for igual a o que vem escrito por padrao, nesse caso "cod", seta o id
            p.setId(Integer.parseInt(Textoid.getText().toString()));
        }
        p.setNome(TextoNome.getText().toString()); // preenche a classe com o que ta escrito
        p.setCelular(TextoCelular.getText().toString());
        p.setEmail(TextoEmail.getText().toString());
        p.setObservacao(TextoObservacao.getText().toString());
        p.setFornecedor(SwitchFornecedor.isChecked()); // 0 -> cliente / 1 -> fornecedor

        BDDados.Salvar(p,getApplicationContext()); //funciona se o metodo de salvar tiver rodando, tem q fazer isso em todas as telas

        setResult(RESULT_OK);
        onBackPressed();
    }

    public void removerPessoa (View view){
        if(!Textoid.getText().toString().equals("cod")) {
            Pessoa p = new Pessoa(); // eu sei que nao precisa fazer isso mas ja ta funcionando assim deixa quieto
            p.setId(Integer.parseInt(Textoid.getText().toString()));
            try {
                BDDados.Remover(p,getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }
            setResult(RESULT_CANCELED);
            onBackPressed();
        } else {
            Toast.makeText(this, "Impossivel salvar pessoa nao salva", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelar(View view){
        setResult(RESULT_CANCELED);
        onBackPressed();
    }
}