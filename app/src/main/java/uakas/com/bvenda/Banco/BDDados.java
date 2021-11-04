package uakas.com.bvenda.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.Nullable;

public class BDDados {
    //necessário: inserir-editar, remover,listar

    public static List Listar(Object o, Context c,@Nullable String[] campos,@Nullable String selecao,@Nullable String[] argumentos){
        BDSetup BD = new BDSetup(c);
        List lista = new LinkedList();
        SQLiteDatabase BDcontrole = BD.getReadableDatabase();
        //metodo generico q vc escreve a query que quiser
        Cursor cursor = BDcontrole.query(o.getClass().getSimpleName(), campos,selecao,argumentos,null,null,null );
        if(cursor.moveToFirst()){
            do {
                //como fazer isso, só consigo pensar em switch case
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public static void Salvar (Object o, Context c){
        //falta o editar, melhor jeito é verificar se id!=null && id>0
        BDSetup BD = new BDSetup(c);
        SQLiteDatabase BDcontrole = BD.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("?","?"); // como fazer isso sem acesso aos metodos, so consigo pensar em switch case
        BDcontrole.insert(o.getClass().getSimpleName(),null,cv);
    }

    public static void Remover (Object o, Context c){
        BDSetup BD = new BDSetup(c);
        SQLiteDatabase BDControle = BD.getWritableDatabase();
        BDControle.delete(o.getClass().getSimpleName(),"id = ?",new String[]{"nsei"}); //como
    }

}
