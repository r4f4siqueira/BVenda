package uakas.com.bvenda;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Locale;

import uakas.com.bvenda.Banco.BDDados;
import uakas.com.bvenda.Entidades.Compra;
import uakas.com.bvenda.Entidades.Conta;
import uakas.com.bvenda.Entidades.EntidadeBanco;
import uakas.com.bvenda.Entidades.ItemCompra;
import uakas.com.bvenda.Entidades.ItemVenda;
import uakas.com.bvenda.Entidades.Pessoa;
import uakas.com.bvenda.Entidades.Produto;
import uakas.com.bvenda.Entidades.Venda;

public class menuLateral extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lateral);
    }

    public void listar (View view){
        Button btn = (Button) view;
        Intent it = new Intent(this, lista.class);
        it.putExtra("entidade",btn.getHint().toString());
        startActivityForResult(it,300);
    }

    public void cadastrar (View view){
        Button btn = (Button) view;
        AppCompatActivity classe;
        switch (btn.getHint().toString()) { // diferenciador pros botoes apertados, tem que ter coisa no "hint" do botao
            case "cliente":
                classe = new pessoa();
                break;
            case "produto":
                classe = new produto();
                break;
            case "fornecedor":
                classe = new pessoa();
                break;
            case "conta":
                classe = new contas();
                break;
            case "compra":
                classe = new compra();
                break;
            case "venda":
                classe = new venda();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + btn.getHint().toString());
        }
        Intent it = new Intent(this,classe.getClass()); //cria a intent da tela da entidade que foi criada, pode dar merda pq tem classe com o msm nome
        startActivityForResult(it,25);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 25){
            Toast.makeText(this, "Cadastro concluido!", Toast.LENGTH_SHORT).show();
        }
    }
}