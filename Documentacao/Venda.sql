create table compra (
	codvenda serial primary key not null,
	id_cliente int,
	descricao varchar(40),
	email varchar(40),
	observacao varchar(40),
	valor decimal(10,2),
	constraint fk_venda_pessoa foreign key (id_cliente) references pessoa(id)
);
