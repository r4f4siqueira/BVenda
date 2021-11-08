package uakas.com.bvenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import uakas.com.bvenda.Banco.BDDados;
import uakas.com.bvenda.Entidades.Compra;
import uakas.com.bvenda.Entidades.Conta;

public class contas extends AppCompatActivity {

    EditText TextoCredorDevedor;
    EditText TextoValor;
    Switch SwitchCompraVenda;
    TextView Textoid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contas);
        TextoCredorDevedor = findViewById(R.id.etContaDevedorCredor);
        TextoValor = findViewById(R.id.etContaValorTotal);
        SwitchCompraVenda = findViewById(R.id.swCompraVenda);
        Textoid = findViewById(R.id.tvContaCod);
    }

    public void salvarConta(View view){
        Conta c = new Conta(); // cria uma nova classe
        c.setValor(Float.parseFloat(TextoValor.getText().toString()));
        if(SwitchCompraVenda.getShowText()){ // coloca o id em compra ou em venda, switch = 1 -> compra / switch = 0 -> venda, nao sei se esse metodo retorna o estado do switch
            c.setId_compra(Integer.parseInt(TextoCredorDevedor.getText().toString()));
        } else {
            c.setId_venda(Integer.parseInt(TextoCredorDevedor.getText().toString()));
        }
        BDDados.Salvar(c,getApplicationContext()); //funciona se o metodo de salvar tiver rodando, tem q fazer isso em todas as telas
        onBackPressed();
    }

    public void menul (View view){
        Intent it = new Intent(this, menuLateral.class);
        startActivityForResult(it,101);
    }

    public void removerConta (View view){
        Conta c = new Conta();
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