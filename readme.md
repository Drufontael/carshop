# API de Revenda de Carros Usados

Esta é uma API Restful desenvolvida em Java, utilizando Spring Boot, PostgreSQL, HATEOAS, Swagger 3 e Docker para uma aplicação de revenda de carros usados.

## Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas antes de começar:

- [Java](https://www.oracle.com/java/)
- [Docker](https://www.docker.com/)

## Configuração do Banco de Dados

A aplicação utiliza o PostgreSQL em um container Docker. Certifique-se de ter o Docker instalado. O spring boot ira rodar 
o docker compose quando inicializar o aplicativo (no Windows, o Docker Desktop deve estar rodando).

1. Configure o banco de dados no `compose.yaml`:

```compose.yaml
services:
  postgres:
    image: 'postgres:latest'
    environment:
      - 'POSTGRES_DB=revenda_carros'
      - 'POSTGRES_PASSWORD=sua_senha'
      - 'POSTGRES_USER=seu_usuario'
    ports:
      - '5432:5432'
```
2. Atualize as configurações do banco de dados no arquivo `application.properties`.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/revenda_carros
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```
## Executando a Aplicação
1. Clone este repositório:
```bash
git clone https://github.com/Drufontael/carshop.git
cd carshop
```
2. Execute a aplicação, o docker compose sera inciado e o banco de dados estara no servidor.

## Documentação da API
A documentação da API foi gerada automaticamente com o Swagger 3 e pode ser acessada em:

```bash
http://localhost:8080/swagger-ui/index.html
```
## Recursos Adicionais
### HATEOAS
A API utiliza o HATEOAS para fornecer links relacionados aos recursos. Certifique-se de explorar esses links nas respostas da API.

## Contribuição
Sinta-se à vontade para contribuir para este projeto. Abra uma issue para discutir novos recursos ou problemas encontrados.

## Licença
Este projeto é licenciado sob a [License MIT](https://github.com/Drufontael/carshop/blob/main/LICENSE).
