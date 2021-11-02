create table itemcompra(
	id serial primary key not null,
	id_compra int,
	id_produto int,
	quantidade decimal (10,2),
	valor_un decimal(10,2),
	constraint fk_itemcompra_compra foreign key (id_compra) references compra(codcompra),
	constraint fk_itemcompra_produto foreign key (id_produto) references produto(codproduto)
);


