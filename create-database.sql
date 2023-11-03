create database BDCadastro;
use BDCadastro;

create table pessoa (
id int auto_increment PRIMARY KEY,
nome varchar(255),
email varchar(255),
endereco varchar(255)
);

insert into pessoa (email, nome, endereco) values ('samy@gmail.com', 'Samylle', 'Rua Mimi 707');
insert into pessoa (email, nome, endereco) values ('luc@gmail.com', 'Luc', 'Rua Mimi 708');
insert into pessoa (email, nome, endereco) values ('luna@gmail.com', 'Luna', 'Rua Mimi 709');
insert into pessoa (email, nome, endereco) values ('sarah@gmail.com', 'Sasa', 'Rua Mimi 710');
insert into pessoa (email, nome, endereco) values ('rubi@gmail.com', 'Rubi', 'Rua Mimi 711');

select email,nome,endereco from pessoa;

ALTER TABLE pessoa
MODIFY COLUMN id SERIAL;