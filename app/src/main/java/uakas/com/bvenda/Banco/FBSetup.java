package uakas.com.bvenda.Banco;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FBSetup {
    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;

    private static void Setup(){
        firebaseDatabase= FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference= firebaseDatabase.getReference();
    }

    public static DatabaseReference getDatabaseReference() {
        if(databaseReference==null){
            Setup();
        }
        return databaseReference;
    }

    public static void salvar(Object o){
        if(databaseReference==null){
            Setup();
        }
        databaseReference.child(o.getClass().getSimpleName()).child("1"/*o.getId().toString()).child("nome").setValue(pessoa.getNome() nao sei*/);
    }
    public void remover(Object o){
        databaseReference.child(o.getClass().getSimpleName()).child("1"/*pessoa.getId()+"" como faz isso */).removeValue();
    }
}
