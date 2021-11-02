create table compra (
	codcompra serial primary key not null,
	id_fornecedor int,
	descricao varchar(40),
	email varchar(40),
	observacao varchar(40),
	valor decimal(10,2),
	constraint fk_compra_pessoa foreign key (id_fornecedor) references pessoa(id)
);
