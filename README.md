# spring api gateway e circuit break
Projeto desenvolvido com fim de aprendizagem de Spring Api Gateway com Circuit Break Resilience4J

## O que é uma API Gateway?
  Basicamente o api gateway é responsável por fazer o roteamento das requisições para as aplicações responsável de acordo com o endpoint e regras pré estabelecidas.
  Por exemplo, suponhamos que temos duas aplicações uma de enviar e-mail e outra de enviar SMS, cada qual com seu proprio endereço e porta de execução. Ao invês do o cliente precisar conhecer o caminho completo (uri + path) de cada aplicação para realizar uma requisição HTTP, podemos concentrar as requisições dos usuários numa aplicação API Gateway que consiste de um portão de requisição que realiza o roteamente para a aplicação alvo responsável pelo processamento solicitado.
  
##  Detalhes da aplicação
  
  - http://localhost:8080/ip  aponta para http://httpbin.org:80/ip
  - http://localhost:8080/viacep/{cep} aponta para https://viacep.com.br/ws/{cep}/json
  - Para simular  caso que o sistema alvo encontra-se indisponível chama-se a rota  http:localhost:8080/delayed dessa forma o circuit break Resilience4J entrará em ação e fará o redirecionamento para a rota http:localhost:8080/fallback com a mensagem "O sistema se encontra indisponível, tente mais tarde!!!".

## Pré requisitos
  - Java 11
  - Apache Maven

## Como rodar?
Para gerar o pacote .jar execute o comando:

```bsh
  mvn clean package
```
Para executar o .jar da aplicação execute :
```bsh
  javar -jar [CAMINHO-ATE-PROJETO]/target/spring-api-gateway-0.0.1-SNAPSHOT.jar
```


  
  
  

