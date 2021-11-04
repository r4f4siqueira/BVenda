package uakas.com.bvenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import uakas.com.bvenda.Banco.BDSetup;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BDSetup Banco = new BDSetup(getApplicationContext());
    }

    public void entrar (View view){
        Intent it = new Intent(this, venda.class);
        startActivityForResult(it,100);
    }
}