create table compra (
	id integer primary key autoincrement not null,
	id_fornecedor int,
	observacao varchar(40),
	valor decimal(10,2),
	constraint fk_compra_pessoa foreign key (id_fornecedor) references pessoa(id)
);
