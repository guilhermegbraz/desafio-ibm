INSERT INTO
    cliente (nome, idade, endereco_email)
VALUES
  ('João Silva', 30, 'joao.slv@example.com'),
  ('Maria Oliveira', 25, 'maria_O@example.com'),
  ('Pedro Santos', 40, 'pedroSNTS@example.com'),
  ('Ana Souza', 35, 'anaazous@example.com'),
  ('Carlos Pereira', 28, 'carlos.p@example.com'),
  ('Juliana Lima', 33, 'julianaLimaa@example.com'),
  ('Rodrigo Costa', 27, 'rodrigoCT@example.com'),
  ('Fernanda Rodrigues', 45, 'fernanda_ro@example.com'),
  ('Lucas Almeida', 29, 'lucas_almeida182@example.com'),
  ('Patricia Gonçalves', 32, 'patriciaG@example.com'),
  ('Gustavo Silva', 37, 'gusta_silva@example.com'),
  ('Camila Santos', 26, 'camilaSant_os@example.com'),
  ('Rafael Oliveira', 31, 'rafael.O@example.com'),
  ('Bianca Costa', 36, 'biancaCostaS@example.com'),
  ('Diego Lima', 38, 'diegoLIMA@example.com'),
  ('Amanda Pereira', 34, 'amanda.p@example.com'),
  ('Paulo Souza', 39, 'pauloSz@example.com'),
  ('Laura Almeida', 28, 'lauraA@example.com'),
  ('Luiz Santos', 29, 'luiZS@example.com'),
  ('Mariana Rodrigues', 31, 'mariana_r@example.com'),
  ('Renato Gonçalves', 27, 'renato_gon@example.com'),
  ('Isabela Silva', 30, 'isabela_sil@example.com'),
  ('Thiago Oliveira', 33, 'thiagoOli@example.com'),
  ('Carolina Costa', 35, 'carolinaAtsoc@example.com'),
  ('Fábio Lima', 42, 'fabio@example.com'),
  ('Julia Pereira', 26, 'julia@example.com'),
  ('Luciana Souza', 29, 'luciana@example.com'),
  ('Vinícius Almeida', 37, 'vinicius@example.com'),
  ('Mariana Gonçalves', 28, 'marianag@example.com'),
  ('Rafaela Santos', 30, 'rafaela@example.com'),
  ('André Oliveira', 32, 'andre@example.com'),
  ('Beatriz Costa', 34, 'beatriz@example.com'),
  ('Lucas Lima', 39, 'lucasL@example.com'),
  ('Gabriela Pereira', 25, 'gabriela@example.com'),
  ('Marcelo Souza', 31, 'marcelo@example.com'),
  ('Nathalia Almeida', 27, 'nathalia@example.com'),
  ('Ricardo Gonçalves', 29, 'ricardo@example.com'),
  ('Vanessa Silva', 35, 'vanessaS@example.com'),
  ('Daniel Oliveira', 40, 'danielOOliv@example.com'),
  ('Ana Carolina Costa', 33, 'anacarolinaC@example.com'),
  ('Guilherme Lima', 28, 'guilhermeLima@example.com'),
  ('Raquel Pereira', 26, 'raquelP1998@example.com'),
  ('Thiago Souza', 37, 'thiago__souz@example.com'),
  ('Larissa Almeida', 30, 'larissa9797@example.com'),
  ('Diego Gonçalves', 29, 'diegogçlv@example.com'),
  ('Aline Silva', 31, 'aline2002@example.com'),
  ('Marcela Oliveira', 38, 'marcelaOl98@example.com');

-- Inserir 50 contas bancárias para os clientes com valores fictícios para saldo e crédito disponível
INSERT INTO conta_bancaria (numero_conta, id_cliente, saldo, credito_disponivel)
SELECT
    LPAD(FLOOR(RAND() * 9999999999), 10, '0') AS numero_conta,
    id,
    ROUND(RAND() * 5000 + 1000, 2) AS saldo,
    ROUND(RAND() * 1000 + 500, 2) AS credito_disponivel
FROM cliente;

SET time_zone = '-03:00';

-- Inserir 30 transações financeiras para cada cliente
INSERT INTO transacao (id_conta, tipo, valor, descricao, data)
SELECT
    cb.id,
    IF(RAND() < 0.5, 'CREDITO', 'DEBITO') AS tipo,
    ROUND((RAND() * 400) - 200, 2) AS valor,
    CASE
        WHEN RAND() < 0.5 THEN CONCAT('Crédito de R$', ROUND((RAND() * 400) - 200, 2), ' na conta')
        ELSE CONCAT('Débito de R$', ROUND((RAND() * 400) - 200, 2), ' na conta')
        END AS descricao,
    NOW() - INTERVAL FLOOR(RAND() * 365) DAY
FROM
    cliente c
        JOIN
    conta_bancaria cb ON c.id = cb.id_cliente
        CROSS JOIN
    (SELECT 1 AS n UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5
     UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10
     UNION ALL SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 UNION ALL SELECT 15
     UNION ALL SELECT 16 UNION ALL SELECT 17 UNION ALL SELECT 18 UNION ALL SELECT 19 UNION ALL SELECT 20
     UNION ALL SELECT 21 UNION ALL SELECT 22 UNION ALL SELECT 23 UNION ALL SELECT 24 UNION ALL SELECT 25
     UNION ALL SELECT 26 UNION ALL SELECT 27 UNION ALL SELECT 28 UNION ALL SELECT 29 UNION ALL SELECT 30) AS dummy;
