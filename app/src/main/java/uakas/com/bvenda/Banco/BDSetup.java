package uakas.com.bvenda.Banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDSetup extends SQLiteOpenHelper {

    public BDSetup(Context context) {
        super(context, "BVenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuffer sql = new StringBuffer();
        //sql.append em cada create table da documentacao, n sei se funciona
        sql.append("create table pessoa (\n" +
                "\tid serial integer primary key autoincrement not null,\n" +
                "\tnome varchar(40),\n" +
                "\tcelular varchar(40),\n" +
                "\temail varchar(40),\n" +
                "\tobservacao varchar(40),\n" +
                "\tfornecedor boolean\n" +
                ");");
        sql.append("create table produto (\n" +
                "\tcodproduto integer primary key autoincrement not null,\n" +
                "\tnome varchar(40),\n" +
                "\tativo boolean,\n" +
                "\tvalor decimal(10,2),\n" +
                "\tqtd_estoque decimal (10,2)\n" +
                ");");
        sql.append("create table compra (\n" +
                "\tcodcompra integer primary key autoincrement not null,\n" +
                "\tid_fornecedor int,\n" +
                "\tobservacao varchar(40),\n" +
                "\tvalor decimal(10,2),\n" +
                "\tconstraint fk_compra_pessoa foreign key (id_fornecedor) references pessoa(id)\n" +
                ");");
        sql.append("create table venda (\n" +
                "\tcodvenda integer primary key autoincrement not null,\n" +
                "\tid_cliente int,\n" +
                "\tdescricao varchar(40),\n" +
                "\tvalor decimal(10,2),\n" +
                "\tconstraint fk_venda_pessoa foreign key (id_cliente) references pessoa(id)\n" +
                ");");
        sql.append("create table itemcompra(\n" +
                "\tid serial integer primary key autoincrement not null,\n" +
                "\tid_compra int,\n" +
                "\tid_produto int,\n" +
                "\tquantidade decimal (10,2),\n" +
                "\tvalor_un decimal(10,2),\n" +
                "\tconstraint fk_itemcompra_compra foreign key (id_compra) references compra(codcompra),\n" +
                "\tconstraint fk_itemcompra_produto foreign key (id_produto) references produto(codproduto)\n" +
                ");");
        sql.append("create table itemvenda (\n" +
                "\tid serial integer primary key autoincrement not null,\n" +
                "\tid_venda int,\n" +
                "\tid_produto int,\n" +
                "\tquantidade decimal (10,2),\n" +
                "\tvalor_un decimal(10,2),\n" +
                "\tconstraint fk_itemvenda_venda foreign key (id_venda) references venda(codvenda),\n" +
                "\tconstraint fk_itemvenda_produto foreign key (id_produto) references produto(codproduto)\n" +
                ");");
        sql.append("create table conta (\n" +
                "\tid serial integer primary key autoincrement not null,\n" +
                "\tid_venda int,\n" +
                "\tid_compra int,\n" +
                "\tid_cliente int,\n" +
                "\tvalor decimal(10,2),\n" +
                "\tconstraint fk_conta_compra foreign key (id_compra) references compra(codcompra),\n" +
                "\tconstraint fk_conta_venda foreign key (id_venda) references venda(codvenda),\n" +
                "\tconstraint fk_conta_pessoa foreign key (id_pessoa) references pessoa(id)\n" +
                ");");

        sqLiteDatabase.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
