package uakas.com.bvenda.Entidades;

import android.content.ContentValues;

import java.util.List;

public interface EntidadeBanco {
    public Integer getId();
    public ContentValues getDadosSalvar();
    public EntidadeBanco setDados(String[] dados);
}
