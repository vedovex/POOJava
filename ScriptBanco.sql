

create table if not exists venda(
id int not null auto_increment,
nome varchar(50) not null,
cpf varchar(12) not null,
data_nascimento varchar(10) not null,
rua varchar(30) not null,
n varchar(4) not null,
cidade varchar(50) not null,
placa varchar(7) not null,
primary key (id)
);

create table if not exists carro(
placa varchar(7) not null ,
cor varchar(20) not null,
modelo varchar(10) not null,
ano varchar(4) not null,
preco decimal(8,2) not null,
situacao varchar(15) not null,
primary key(placa)
);

alter table venda
add constraint Fk_venda_carro
foreign key(placa)
references carro(placa);

create table if not exists servico(
id int not null,
descricao int not null,
periodo varchar(10) not null,
placa varchar(7) not null,
primary key(id)
);

alter table servico 
add constraint FK_CARRO_SERV
foreign key(placa)
references carro(placa);

create table if not exists desc_servico(
id int auto_increment not null,
descricao varchar(20) not null
);

alter table venda
add constraint FK_DESC_SERV_VENDA
foreign key(descricao)
references desc_servico(id);



