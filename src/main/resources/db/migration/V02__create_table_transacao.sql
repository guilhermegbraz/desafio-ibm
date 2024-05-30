CREATE TABLE transacao (
    id int auto_increment primary key ,
    id_conta int not null ,
    tipo enum('CREDITO', 'DEBITO') not null,
    valor decimal(10, 2) not null,
    data timestamp default current_timestamp,
    descricao varchar(255),
    foreign key (id_conta) references  conta_bancaria(id)
);