create table produto (
	codproduto integer primary key autoincrement not null,
	nome varchar(40),
	ativo boolean,
	valor decimal(10,2),
	qtd_estoque decimal (10,2)
);

