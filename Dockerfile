
# Use uma imagem base do Maven para realizar o build da aplicação
FROM maven:3.8.4-openjdk-17 AS build
LABEL authors="guilherme"
# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo pom.xml para o contêiner
COPY pom.xml .

# Baixa as dependências do Maven (somente as dependências)
RUN mvn dependency:go-offline -B

# Copia o código-fonte para o contêiner
COPY ./src ./src

# Realiza o build da aplicação
RUN mvn package -DskipTests

# Segunda etapa: cria a imagem final
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

# Copia o JAR gerado na etapa de build para a imagem final
COPY --from=build /app/target/desafio-ibm-0.0.1-SNAPSHOT.jar ./desafio-ibm-0.0.1-SNAPSHOT.jar

# Define o comando padrão para executar a aplicação
CMD ["java", "-jar", "desafio-ibm-0.0.1-SNAPSHOT.jar"]
