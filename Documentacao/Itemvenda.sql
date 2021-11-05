create table itemvenda (
	id serial integer primary key autoincrement not null,
	id_venda int,
	id_produto int,
	quantidade decimal (10,2),
	valor_un decimal(10,2),
	constraint fk_itemvenda_venda foreign key (id_venda) references venda(id),
	constraint fk_itemvenda_produto foreign key (id_produto) references produto(id)
);

