create table venda (
	codvenda integer primary key autoincrement not null,
	id_cliente int,
	descricao varchar(40),
	valor decimal(10,2),
	constraint fk_venda_pessoa foreign key (id_cliente) references pessoa(id)
);
