package uakas.com.bvenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.core.Tag;

import uakas.com.bvenda.Banco.BDSetup;

public class MainActivity extends AppCompatActivity {

    private EditText login;
    private EditText senha;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BDSetup Banco = new BDSetup(getApplicationContext());

        login=findViewById(R.id.etLogin);
        senha=findViewById(R.id.etSenha);
        mAuth = FirebaseAuth.getInstance();
    }

    public void entrar (){
        Intent it = new Intent(this, venda.class);
        startActivityForResult(it,100);
    }

    public void createUser(View view){
        if (login.getText().toString().equals("")||senha.getText().toString().equals("")||senha.getText().toString().length()<6){
            Toast.makeText(MainActivity.this, "Erro ao Criar conta",Toast.LENGTH_SHORT).show();
        }else {
        mAuth.createUserWithEmailAndPassword(login.getText().toString(), senha.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Conta Criada",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("eRRO", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Nao possivel Criar",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
        }
    }

    public void autenticacao(View view){
        if (login.getText().toString().equals("")||senha.getText().toString().equals("")){
            Toast.makeText(MainActivity.this, "Login ou Senha nulo",Toast.LENGTH_SHORT).show();
        }else {
            mAuth.signInWithEmailAndPassword(login.getText().toString(), senha.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                //Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(MainActivity.this, "Entrou",Toast.LENGTH_SHORT).show();
                                entrar();
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Erro ao entrar",Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });
        }
    }

}