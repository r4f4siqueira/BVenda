create table pessoa (
	id serial integer primary key autoincrement not null,
	nome varchar(40),
	celular varchar(40),
	email varchar(40),
	observacao varchar(40),
	fornecedor boolean
);
