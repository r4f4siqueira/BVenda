package uakas.com.bvenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import uakas.com.bvenda.Banco.BDDados;
import uakas.com.bvenda.Entidades.Compra;
import uakas.com.bvenda.Entidades.Pessoa;

public class pessoa extends AppCompatActivity {
    EditText TextoNome; //colocar o tipo do campo e o nome seguindo esse padrao
    EditText TextoCelular;
    EditText TextoEmail;
    EditText TextoObservacao;
    TextView Textoid;
    Switch SwitchFornecedor;

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
    }

    public void salvarpessoa(View view){
        Pessoa p = new Pessoa(); // cria uma nova classe
        p.setNome(TextoNome.getText().toString()); // preenche a classe com o que ta escrito
        p.setCelular(TextoCelular.getText().toString());
        p.setEmail(TextoEmail.getText().toString());
        p.setObservacao(TextoObservacao.getText().toString());
        p.setFornecedor(SwitchFornecedor.getShowText()); // 0 -> cliente / 1 -> fornecedor
        BDDados.Salvar(p,getApplicationContext()); //funciona se o metodo de salvar tiver rodando, tem q fazer isso em todas as telas
    }

    public void removerPessoa (View view){
        Pessoa p = new Pessoa();
        p.setId(Integer.parseInt(Textoid.getText().toString()));
        try {
            BDDados.Remover(p,getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        onBackPressed();
    }

    public void cancelar(View view){
        onBackPressed();
    }
}