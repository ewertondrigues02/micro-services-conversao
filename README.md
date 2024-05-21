# Microserviço de Conversão de Moedas
Este projeto demonstra a implementação de um sistema de microserviços para conversão de moedas, utilizando uma série de tecnologias modernas e robustas. O sistema é projetado para ser escalável, resiliente e fácil de manter.

## Tecnologias Utilizadas
* Java
* Spring Boot
* Spring Cloud
* Eureka
* Feign
* Spring Boot Actuator
* Spring Cloud Gateway
* Resilience4j
* Swagger OpenAPI
  
## Arquitetura
A arquitetura do sistema é composta pelos seguintes componentes:

**Eureka Server:** Serviço de registro e descoberta de microserviços.  
**Currency Exchange Service:** Fornece as taxas de câmbio entre diferentes moedas.  
**Currency Conversion Service:** Converte valores entre diferentes moedas usando as taxas fornecidas pelo Currency Exchange Service.  
**API Gateway:** Gerencia todas as solicitações de entrada e direciona para os microserviços apropriados.  
**Resilience4j:** Implementa padrões de resiliência como Circuit Breaker, Rate Limiter e Retry.  
**Swagger OpenAPI:** Documentação interativa das APIs dos microserviços.  

  
