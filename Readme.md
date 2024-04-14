# Teste - Projeto de Ecommerce de pedidos

O projeto consiste em uma api de notificação de pedidos. 

## Stack
- Intellij - IDE
- Linguagem kotlin - Versão: 1.5.0 
- Spring boot 3.2.2 
- kotlin
- Apache Kafka

### Banco NoSQL 
- MongoDB

### Documentação

- Swagger 2.9.2
- http://localhots:8080/swagger-ui.html

### Projeto

## Run
 ### Execute o programa:
   $ ./gradlew clean build \
 ou \
   $ docker build --no-cache -t payments:latest . \
   $ docker run -p 8080:8080 payments:latest \
* Para subir o kafka pelo docker \
   $ docker-compose up -d
 
### REST API
* Lista todas a orders: \
GET /api/v1/orders/search \
  RESPONSE:


* Busca order por id:\
GET /api/v1/orders/1 \
  RESPONSE:


* Cria uma order:
POST /api/v1/orders \
  RESPONSE:
