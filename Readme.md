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
- <http://localhots:8080/swagger-ui.html>

### Projeto
Implementar um protótipo da integração de processamento de pedidos.
Mais especificamente, deve atender pelo menos aos requisitos abaixo:
- [x] Receber notificações de pedidos (novos e atualizações)
- [x] Salvar no banco (MongoDB, de preferência)
- [x] Permitir listagem de pedidos
- [x] Emular uma fila para gerar as notificações, as quais o serviço deve consumir

Exemplo de pedido:
```{
"orderId": "100-1234567-7654321",
"createdAt": 1707504821000,
"updatedAt": 1707504821000,
"status": "NEW", // possible values: NEW, APPROVED, FINISHED, CANCELLED
"items": [
{
"itemId": "ABC0123456789",
"sku": "SKU_DO_SELLER_123",
"name": "Desempenador de pipa",
"description": "O melhor desempenador de pipa que você verá na sua vida.
Tração 4x4 e efeitos sonoros ideais para o seu churrasco",
"price": 1499.99,
"url": "https://www.minha-lojinha.com.br/products/ABC0123456789"
}
],
"seller": "ABC1F2DGHC",
"buyer": {
"id": "BCD2F2CCBA",
"name": "Pietro Alcantara",
"email": "p2alcantara_teste@gmail.com"
},
"shippingAddress": {
"postalCode": "04540-010",
"streetName": "Rua do bobos",
"number": "456 A",
"additionalInfo": "Apto 37"
},
"billingAddress": {
"postalCode": "03333-310",
"streetName": "Rua do sellers",
"number": "333",
"additionalInfo": "Próximo ao metrô cacimbas"
},
"payment": {
"method": "CREDIT", // possible values: CREDIT, DEBIT, GIFT_CARD, OTHER
"ammount": 1499.99,
"status": "PENDING" // possible values: PENDING, APPROVED, REFUSED
}
}
```
### Run
 #### Execute:
   - $ ./gradlew clean build \
 ou 
   - $ docker build --no-cache -t payments:latest . 
   - $ docker run -p 8080:8080 payments:latest \
 #### Para subir o kafka pelo docker:
   - $ docker-compose up -d
 
### REST API
* Lista todas a orders: \
  <b>GET</b> /api/v1/orders/search \
  RESPONSE:
  

* Busca order por id:\
<b>GET</b> /api/v1/orders/1 

  RESPONSE: \
  200 \
  OK

  Exemplo de pedido:
  ```
  {
  "orderId": "100-1234567-7654321",
  "createdAt": 1707504821000,
  "updatedAt": 1707504821000,
  "status": "NEW", // possible values: NEW, APPROVED, FINISHED, CANCELLED
  "items": [
  {
  "itemId": "ABC0123456789",
  "sku": "SKU_DO_SELLER_123",
  "name": "Desempenador de pipa",
  "description": "O melhor desempenador de pipa que você verá na sua vida.
  Tração 4x4 e efeitos sonoros ideais para o seu churrasco",
  "price": 1499.99,
  "url": "https://www.minha-lojinha.com.br/products/ABC0123456789"
  }
  ],
  "seller": "ABC1F2DGHC",
  "buyer": {
  "id": "BCD2F2CCBA",
  "name": "Pietro Alcantara",
  "email": "p2alcantara_teste@gmail.com"
  },
  "shippingAddress": {
  "postalCode": "04540-010",
  "streetName": "Rua do bobos",
  "number": "456 A",
  "additionalInfo": "Apto 37"
  },
  "billingAddress": {
  "postalCode": "03333-310",
  "streetName": "Rua do sellers",
  "number": "333",
  "additionalInfo": "Próximo ao metrô cacimbas"
  },
  "payment": {
  "method": "CREDIT", // possible values: CREDIT, DEBIT, GIFT_CARD, OTHER
  "ammount": 1499.99,
  "status": "PENDING" // possible values: PENDING, APPROVED, REFUSED
  }
  }
  ```
* Cria uma order: \
<b>POST</b> /api/v1/orders \
  RESPONSE: \
  201 \
  Created
