create table itemcompra(
	id serial integer primary key autoincrement not null,
	id_compra int,
	id_produto int,
	quantidade decimal (10,2),
	valor_un decimal(10,2),
	constraint fk_itemcompra_compra foreign key (id_compra) references compra(id),
	constraint fk_itemcompra_produto foreign key (id_produto) references produto(id)
);


