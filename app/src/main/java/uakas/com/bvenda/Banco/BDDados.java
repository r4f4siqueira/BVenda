package uakas.com.bvenda.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.Nullable;

import uakas.com.bvenda.Entidades.EntidadeBanco;
import uakas.com.bvenda.Entidades.Produto;

public class BDDados {
    //necessário: inserir-editar, remover,listar

    public static List Listar(EntidadeBanco o, Context c, @Nullable String[] campos, @Nullable String selecao, @Nullable String[] argumentos){
        BDSetup BD = new BDSetup(c);
        List lista = new LinkedList();
        SQLiteDatabase BDcontrole = BD.getReadableDatabase();
        //metodo generico q vc escreve a query que quiser
        Cursor cursor = BDcontrole.query(o.getClass().getSimpleName().toLowerCase(Locale.ROOT), campos,selecao,argumentos,null,null,null);
        if(cursor.moveToFirst()){
            do {
                String[] dados = new String[cursor.getColumnCount()];
                for(int i = 0;i < dados.length;i++) {
                    switch (cursor.getType(i)){
                        case 0: // 0-> null / 1 -> int / 2 -> float / 3 -> string / 4 -> blob
                            break;
                        case 1:
                            dados[i] = cursor.getInt(i)+"";
                            break;
                        case 2:
                            dados[i] = cursor.getFloat(i)+"";
                            break;
                        case 3:
                            dados[i] = cursor.getString(i)+"";
                            break;
                    }
                }
                EntidadeBanco eb = null;
                try {
                    eb = EntidadeBanco.class.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                eb = o.setDados(dados);
                lista.add(eb); // nao sei pq mas no final todos os itens da lista viram o ultimo, e o ultimo ta embaralhado
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public static void Salvar (EntidadeBanco o, Context c){
        //falta o editar, melhor jeito é verificar se id!=null && id>0
        BDSetup BD = new BDSetup(c);
        SQLiteDatabase BDcontrole = BD.getWritableDatabase();
        ContentValues cv = o.getDadosSalvar();
//        Produto p = new Produto(); exemplo de como faria sem ser generico
//        cv.put("nomeproduto",p.getNome());
        BDcontrole.insert(o.getClass().getSimpleName(),null,cv);
    }

    public static void Remover (EntidadeBanco o, Context c) throws Exception {
        BDSetup BD = new BDSetup(c);
        SQLiteDatabase BDControle = BD.getWritableDatabase();
        if(o.getId() != null && o.getId() > 0){
            BDControle.delete(o.getClass().getSimpleName(),"id = ?",new String[]{o.getId()+""}); //como
        } else {
            //nao deveria cair aqui
            throw new Exception("deletando objeto sem id");
        }

    }

    public static Integer getlastid(String tabela,String[] campos, Context c){
        BDSetup BD = new BDSetup(c);
        Integer id = null;
        SQLiteDatabase BDcontrole = BD.getReadableDatabase();
        //metodo generico q vc escreve a query que quiser
        Cursor cursor = BDcontrole.query(tabela,campos,null,null,null,null,"id DESC","1");
        if(cursor.moveToFirst()){
            do {
                id = cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        if (id == null){
            return 0;
        } else {
            return id;
        }

    }

}
