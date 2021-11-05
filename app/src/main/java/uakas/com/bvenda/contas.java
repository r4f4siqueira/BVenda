package uakas.com.bvenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;

import uakas.com.bvenda.Banco.BDDados;
import uakas.com.bvenda.Entidades.Compra;
import uakas.com.bvenda.Entidades.Conta;

public class contas extends AppCompatActivity {

    EditText TextoCredorDevedor;
    EditText TextoValor;
    Switch SwitchCompraVenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contas);
        TextoCredorDevedor = findViewById(R.id.etContaDevedorCredor);
        TextoValor = findViewById(R.id.etContaValorTotal);
        SwitchCompraVenda = findViewById(R.id.swCompraVenda);
    }

    public void salvarConta(){
        Conta c = new Conta(); // cria uma nova classe
        c.setValor(Float.parseFloat(TextoValor.getText().toString()));
        if(SwitchCompraVenda.getShowText()){ // coloca o id em compra ou em venda, switch = 1 -> compra / switch = 0 -> venda, nao sei se esse metodo retorna o estado do switch
            c.setId_compra(Integer.parseInt(TextoCredorDevedor.getText().toString()));
        } else {
            c.setId_venda(Integer.parseInt(TextoCredorDevedor.getText().toString()));
        }
        BDDados.Salvar(c,getApplicationContext()); //funciona se o metodo de salvar tiver rodando, tem q fazer isso em todas as telas
    }
}