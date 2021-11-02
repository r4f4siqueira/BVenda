package uakas.com.bvenda.Entidades;

import java.io.Serializable;

public class Conta implements Serializable {
    /*
    create table conta (
            id serial primary key not null,
            id_venda int,
            id_compra int,
            id_cliente int,
            valor decimal(10,2),
            constraint fk_conta_compra foreign key (id_compra) references compra(codcompra),
            constraint fk_conta_venda foreign key (id_venda) references venda(codvenda),
            constraint fk_conta_pessoa foreign key (id_pessoa) references pessoa(id)
    );

    */
}
