create table pessoa (
	id serial primary key not null,
	nome varchar(40),
	celular varchar(40),
	email varchar(40),
	observacao varchar(40),
	fornecedor boolean
);
