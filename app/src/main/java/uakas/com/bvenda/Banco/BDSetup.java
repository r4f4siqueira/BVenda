package uakas.com.bvenda.Banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDSetup extends SQLiteOpenHelper {

    public BDSetup(Context context) {
        super(context, "BVenda", null, 13);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { //assim deve executar de uma vez //verificar se está igual a ordem com as entidades
        StringBuilder sql = new StringBuilder();
        sql.append("create table if not exists pessoa(\n" +
                "\tid integer primary key autoincrement not null,\n" +
                "\tnome varchar(40),\n" +
                "\tcelular varchar(40),\n" +
                "\temail varchar(40),\n" +
                "\tobservacao varchar(40),\n" +
                "\tfornecedor boolean\n" +
                ");");
        sqLiteDatabase.execSQL(sql.toString());
        sql = new StringBuilder();
        sql.append("create table if not exists produto(\n" +
                "id integer primary key autoincrement not null,\n" +
                "nome       varchar(40),\n" +
                "ativo      boolean,\n" +
                "valor      decimal(10,2),\n" +
                "qtd_estoque decimal (10,2)\n" +
                ");");
        sqLiteDatabase.execSQL(sql.toString());
        sql = new StringBuilder();
        sql.append("create table if not exists compra (\n" +
                "\tid integer primary key autoincrement not null,\n" +
                "\tid_fornecedor int,\n" +
                "\tobservacao varchar(40),\n" +
                "\tvalor decimal(10,2),\n" +
                "\tconstraint fk_compra_pessoa foreign key (id_fornecedor) references pessoa(id)\n" +
                ");");
        sqLiteDatabase.execSQL(sql.toString());
        sql = new StringBuilder();
        sql.append("create table if not exists venda(\n" +
                "\tid integer primary key autoincrement not null,\n" +
                "\tid_cliente int,\n" +
                "\tdescricao varchar(40),\n" +
                "\tvalor decimal(10,2),\n" +
                "\tconstraint fk_venda_pessoa foreign key (id_cliente) references pessoa(id)\n" +
                ");");
        sqLiteDatabase.execSQL(sql.toString());
        sql = new StringBuilder();
        sql.append("create table if not exists itemcompra(\n" +
                "\tid integer primary key autoincrement not null,\n" +
                "\tid_compra int,\n" +
                "\tid_produto int,\n" +
                "\tquantidade decimal (10,2),\n" +
                "\tvalor_un decimal(10,2),\n" +
                "\tconstraint fk_itemcompra_compra foreign key (id_compra) references compra(codcompra),\n" +
                "\tconstraint fk_itemcompra_produto foreign key (id_produto) references produto(codproduto)\n" +
                ");");
        sqLiteDatabase.execSQL(sql.toString());
        sql = new StringBuilder();
        sql.append("create table if not exists itemvenda(\n" +
                "\tid integer primary key autoincrement not null,\n" +
                "\tid_venda int,\n" +
                "\tid_produto int,\n" +
                "\tquantidade decimal (10,2),\n" +
                "\tvalor_un decimal(10,2),\n" +
                "\tconstraint fk_itemvenda_venda foreign key (id_venda) references venda(codvenda),\n" +
                "\tconstraint fk_itemvenda_produto foreign key (id_produto) references produto(codproduto)\n" +
                ");");
        sqLiteDatabase.execSQL(sql.toString());
        sql = new StringBuilder();
        sql.append("create table if not exists conta(\n" +
                "\tid integer primary key autoincrement not null,\n" +
                "\tid_venda int,\n" +
                "\tid_compra int,\n" +
                "\tid_cliente int,\n" +
                "\tvalor decimal(10,2),\n" +
                "\tconstraint fk_conta_compra foreign key (id_compra) references compra(codcompra),\n" +
                "\tconstraint fk_conta_venda foreign key (id_venda) references venda(codvenda),\n" +
                "\tconstraint fk_conta_pessoa foreign key (id_cliente) references pessoa(id)\n" +
                ");");
        sqLiteDatabase.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {//acho que assim funciona de uma vez
        StringBuilder sql = new StringBuilder();
        sql.append("create table if not exists pessoa(\n" +
                "\tid integer primary key autoincrement not null,\n" +
                        "\tnome varchar(40),\n" +
                        "\tcelular varchar(40),\n" +
                        "\temail varchar(40),\n" +
                        "\tobservacao varchar(40),\n" +
                        "\tfornecedor boolean\n" +
                        ");");
        sqLiteDatabase.execSQL(sql.toString());
        sql = new StringBuilder();
        sql.append("create table if not exists produto(\n" +
                "id integer primary key autoincrement not null,\n" +
                "nome       varchar(40),\n" +
                "ativo      boolean,\n" +
                "valor      decimal(10,2),\n" +
                "qtd_estoque decimal (10,2)\n" +
                ");");
        sqLiteDatabase.execSQL(sql.toString());
        sql = new StringBuilder();
        sql.append("create table if not exists compra (\n" +
                "\tid integer primary key autoincrement not null,\n" +
                "\tid_fornecedor int,\n" +
                "\tobservacao varchar(40),\n" +
                "\tvalor decimal(10,2),\n" +
                "\tconstraint fk_compra_pessoa foreign key (id_fornecedor) references pessoa(id)\n" +
                ");");
        sqLiteDatabase.execSQL(sql.toString());
        sql = new StringBuilder();
        sql.append("create table if not exists venda(\n" +
                "\tid integer primary key autoincrement not null,\n" +
                "\tid_cliente int,\n" +
                "\tdescricao varchar(40),\n" +
                "\tvalor decimal(10,2),\n" +
                "\tconstraint fk_venda_pessoa foreign key (id_cliente) references pessoa(id)\n" +
                ");");
        sqLiteDatabase.execSQL(sql.toString());
        sql = new StringBuilder();
        sql.append("create table if not exists itemcompra(\n" +
                "\tid integer primary key autoincrement not null,\n" +
                "\tid_compra int,\n" +
                "\tid_produto int,\n" +
                "\tquantidade decimal (10,2),\n" +
                "\tvalor_un decimal(10,2),\n" +
                "\tconstraint fk_itemcompra_compra foreign key (id_compra) references compra(codcompra),\n" +
                "\tconstraint fk_itemcompra_produto foreign key (id_produto) references produto(codproduto)\n" +
                ");");
        sqLiteDatabase.execSQL(sql.toString());
        sql = new StringBuilder();
        sql.append("create table if not exists itemvenda(\n" +
                "\tid integer primary key autoincrement not null,\n" +
                "\tid_venda int,\n" +
                "\tid_produto int,\n" +
                "\tquantidade decimal (10,2),\n" +
                "\tvalor_un decimal(10,2),\n" +
                "\tconstraint fk_itemvenda_venda foreign key (id_venda) references venda(codvenda),\n" +
                "\tconstraint fk_itemvenda_produto foreign key (id_produto) references produto(codproduto)\n" +
                ");");
        sqLiteDatabase.execSQL(sql.toString());
        sql = new StringBuilder();
        sql.append("create table if not exists conta(\n" +
                "\tid integer primary key autoincrement not null,\n" +
                "\tid_venda int,\n" +
                "\tid_compra int,\n" +
                "\tid_cliente int,\n" +
                "\tvalor decimal(10,2),\n" +
                "\tconstraint fk_conta_compra foreign key (id_compra) references compra(codcompra),\n" +
                "\tconstraint fk_conta_venda foreign key (id_venda) references venda(codvenda),\n" +
                "\tconstraint fk_conta_pessoa foreign key (id_cliente) references pessoa(id)\n" +
                ");");
        sqLiteDatabase.execSQL(sql.toString());

    }
}
