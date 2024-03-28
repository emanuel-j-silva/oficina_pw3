create table consertos(
 id bigint not null auto_increment,
 data_entrada varchar(100) not null,
 data_saida varchar(100) not null,
 nome varchar(100) not null,
 anos_exp bigint not null,
 marca varchar(100) not null,
 modelo varchar(100) not null,
 ano bigint not null,
 primary key(id)
);