CREATE TABLE cliente(
    id int not null auto_increment primary key,
    nome varchar(100) not null,
    idade int not null,
    endereco_email varchar(100) not null unique
);

CREATE TABLE conta_bancaria(
    id int not null auto_increment primary key,
    numero_conta varchar(10) unique not null ,
    id_cliente int not null,
    saldo DECIMAL(10, 2) NOT NULL DEFAULT 0,
    credito_disponivel DECIMAL(10, 2) NOT NULL DEFAULT 0,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);