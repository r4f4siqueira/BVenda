create table venda (
	id integer primary key autoincrement not null,
	id_cliente int,
	descricao varchar(40),
	valor decimal(10,2),
	concluido boolean,
	constraint fk_venda_pessoa foreign key (id_cliente) references pessoa(id)
);
