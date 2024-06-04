# Desafio IBM

Este projeto foi desenvolvido para ser submetido à IBM, como resposta ao desafio técnico proposto. Segue a baixo o enunciado do desafio:

## Enunciado
O desafio consiste em criar uma aplicação web que simule transações financeiras de um banco.

O enunciado pede que a aplicação possua as seguintes funcionalidades:
* Cadastro de clientes com os campos: nome, idade, endereço de email e número da conta.
* Cadastro de Débito e Crédito na conta do cliente.
* Extrato em tela da conta do cliente com saldo total (no rodapé ou no topo da página).

A aplicação poderá ser feita conforme abaixo:

* FrontEnd: Angular.
* Backend: Java(SpringBoot).
* Banco de dados: MySQL, SQL Server, Mongo ou PostgreSQL, arquivo texto (JSON).

## Técnologias

A aplicação foi desenvolvida utilizando:
* Java 17: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
* SpringBoot
* Maven
* Mysql 8.0
* Docker (Utilizado para subir banco de dados e backend caso não queira instalar todas as ferramentas necessárias na máquina)

## Instalação

### Utilizando Docker
Caso você tenha o docker instalado na sua máquina, basta subir o docker compose, o processo vai subir um container com o Banco MySQL e as variáveis de ambiente configuradas, buildar um dockerfile, com a imagem do maven, 
que faz o download das dependencias e copia, builda e executa o jar da aplicação Spring. Basta navegar até a pasta raiz do projeto e rodar o comando:
```bash
docker-compose up
```
O banco irá ser exposto na porta 3306, e a aplicação na porta 8080. Então certifique-se de que essas portas estão liberadas ou altere o docker-compose.yaml para utilizar portas disponíveis.

Obs: Na pasta src/main/resources/compose-db-dev há um docker-compose.yaml que sobe apenas o banco MySQL caso queira rodar a aplicação na própria máquina mas não tenha o MySQL 8 instalado.

### Maquina local
Certifique-se de instalar todas as técnologias necessarias. Então:

1 -  Clone o repositório:
```bash
git clone https://github.com/guilhermegbraz/desafio-ibm.git
```
2 - Instale as depêndencias
```bash
./mvnw clean install
```
3 - Banco
Crie um database no MySQL com o nome "banco_ibm", usuário "user" e senha "password", ou altere o arquivo application.properties para utilizar o database, usuario e senha que você settar no seu ambiente.

4 - Subir a aplicação
Na raiz do repositório git, execute o comando
```bash
./mvnw spring-boot:run
```
A aplicação subirá na porta 8080, então certifique-se da que a porta está disponível.

## Uso

Após subir a API, é possível ver a documentação completa em: http://localhost:8080/swagger-ui/index.html#/

A API possui endpoints para:
* Cadastro de cliente: Para cadastrar um cliente, você deve fazer um post para o endpoint /cliente enviando um json com os campos: nome; idade; email; O campo "numeroConta" é opcional.
  Ao cadastrar um cliente, a API cria uma conta para esse cliente e devolve os dados do cliente cadastrado e sua conta bancaria.
* Listar clientes: Para listar os cliente, faça um get para /cliente. O retorno é a lista de clientes paginado, você pode mandar parametros como size(quantidade de elementos na lista) e page(qual pagina quer) na url da requisição.
* Detalhar um cliente: para detalhar um cliente, faça um get para /cliente/<ID_DO_CLIENTE>
* Listar contas: Faça um get para /conta, o resultado também é paginado e aceita os parametros de número da página e tamanho.
* Detalhar uma conta: Faça um  get para /conta/<ID_DA_CONTA>
* Listar transações de uma conta: Faça um get para /conta/<ID_DA_CONTA>/transações, o resultado também é paginado e aceita os parametros de número da página e tamanho.
* Cadastro de transações: Para cadastrar uma transação, você deve fazer um post para o endpoint /transação enviando um json com os campos: idConta; tipo; valor; O campo "descricao" é opcional.
* Detalhar uma transação: para detalhar uma transação, faça um get para /transação/<ID_DA_TRANSACAO>

O Frontend está disponível no repositório: https://github.com/guilhermegbraz/transacoes-financeiras-desafio-IBM-frontend

















