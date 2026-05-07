# TSM-Atelier - API de E-commerce de LuxoI

![Java](https://img.shields.io/badge/Java-21-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-brightgreen.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue.svg)
![Redis](https://img.shields.io/badge/Redis-7-red.svg)
![Stripe](https://img.shields.io/badge/Stripe-Payments-blueviolet.svg)

Este projeto é um laboratório pessoal. Ele nasceu com o objetivo de aprofundar meus estudos e evoluir minha percepção do que significa atuar como um verdadeiro **Engenheiro de Software**.

Durante a construção deste sistema, o foco não foi apenas fazer o código funcionar, mas sim entender o *como* e o *porquê* das decisões arquiteturais: como escalar, como lidar com concorrência, como proteger rotas e como manter o domínio do negócio limpo e isolado de ferramentas externas.

## 🚀 Tech Stack

*   **Core:** Java 21, Spring Boot 3, Spring Data JPA, Spring Security
*   **Database:** PostgreSQL (Dados Relacionais), Flyway (*Database Migrations*)
*   **Caching & Rate Limiting:** Redis (Memória Distribuída)
*   **Integrations:** Stripe (*Payment Gateway*), Resend (E-mails Transacionais), S3/MinIO (*Object Storage*)
*   **Tooling:** MapStruct (Mapeamento de DTOs), Lombok, Swagger/OpenAPI (Documentação), Spring Boot Actuator (Observabilidade)
*   **Testing:** JUnit 5, Mockito, MockMvc

## 🏗️ O que eu aprendi: Destaques de Arquitetura e Engenharia

Ao longo deste projeto, foquei em resolver desafios complexos do mundo real com soluções de nível *Enterprise*. Estas foram as minhas maiores evoluções e implementações:

### 1. Clean Architecture (Ports & Adapters)
Aprendi a blindar as regras de negócio do meu sistema contra dependências externas. A integração com o Stripe segue o padrão de *Hexagonal Architecture*. O `OrderService` (coração do domínio) interage apenas com uma interface pura de domínio (`PaymentGateway`). Os DTOs específicos da API do Stripe e as requisições HTTP estão isolados dentro do adaptador `StripePaymentGateway`. Isso evita o *Infrastructure Leakage* (Vazamento de Infraestrutura). Se amanhã a loja decidir usar o PayPal, meu domínio principal permanece intacto.

### 2. Gestão de Estoque de Alta Concorrência (Pessimistic Locking)
Compreender concorrência em banco de dados foi um divisor de águas. O e-commerce de luxo depende de escassez artificial. Para evitar o bug crítico de *over-selling* (dois usuários comprando o último item no mesmo milissegundo), estudei e implementei o **Pessimistic Locking** (`SELECT FOR UPDATE` via `@Lock(LockModeType.PESSIMISTIC_WRITE)`). Agora o sistema garante dedução atômica e bloqueia acessos concorrentes à mesma linha de estoque de forma segura, evitando que clientes sejam cobrados por itens que não existem.

### 3. Rate Limiting Distribuído (Anti-DDoS & Brute Force)
Segurança em ambientes distribuídos não pode depender da memória do servidor. Entendi a necessidade de criar um *Distributed Rate Limiter*. Usando a atomicidade do **Redis** (operações de incremento e TTL), criei um sistema de proteção contra ataques de força bruta. Se o projeto estiver rodando em 5 servidores diferentes, todos eles respeitarão o mesmo limite (ex: 5 tentativas de login por IP).

### 4. Filtros Dinâmicos de Alta Performance (Spring Data Specifications)
O catálogo precisava de filtros combinados (Categoria, Tamanho, Preço). Aprender a não usar JPQL estáticas gigantes foi essencial. Utilizei a *Criteria API* (`JpaSpecificationExecutor`) do Spring Data para montar cláusulas `WHERE` e `JOINs` SQL de forma totalmente dinâmica. O banco de dados só faz o cruzamento de tabelas (JOIN) se o usuário realmente aplicar um filtro que exija isso, melhorando radicalmente a performance e resolvendo problemas crônicos de *N+1 queries*.

### 5. Autenticação JWT Verdadeiramente Stateless
Estudei a fundo como o JWT foi feito para brilhar em sistemas distribuídos (*Stateless Identity Projection*). O meu `JwtAuthenticationFilter` valida a assinatura criptográfica e constrói a identidade do usuário na memória usando exclusivamente os *claims* de dentro do token. Isso elimina a necessidade de fazer um `SELECT` na tabela de usuários a cada clique que o cliente dá na loja, liberando o banco de dados de uma carga enorme.

### 6. Processamento Assíncrono e Resiliência (Webhooks)
Trabalhar com o Stripe exigiu entender comunicação entre sistemas via Webhooks. Implementei a validação criptográfica da assinatura enviada pelo Stripe (para evitar que *hackers* falsifiquem pagamentos) e preparei o sistema para a natureza assíncrona de pagamentos. Se a janela de pagamento do cliente expirar, o webhook garante que o pedido seja cancelado e que a roupa volte instantaneamente para a vitrine.

### 7. Observabilidade & Tracing
Entendi que um engenheiro sênior sabe o que está acontecendo com o software em produção.
*   **MDC (Mapped Diagnostic Context):** Criei um `TraceIdFilter` que dá um "RG" (ID) para cada requisição. Esse ID aparece em todos os logs, me permitindo rastrear o caminho de um erro num oceano de informações.
*   **Structured Logging & AOP:** Usei *Programação Orientada a Aspectos* (AOP) para interceptar os métodos e logar métricas e erros de forma padronizada. Criei também regras *anti-over-logging* para evitar que listas gigantes travem o terminal.
*   **Actuator:** Liberei endpoints de saúde e métricas, mostrando que o projeto está pronto para o ecossistema de *DevOps* e *Cloud*.

## 📖 Documentação da API

A API é totalmente documentada usando OpenAPI 3.0. Quando a aplicação estiver rodando, você pode explorar e testar todos os endpoints de forma interativa.

*   **Swagger UI:** `http://localhost:8080/swagger-ui.html`

*Dica: Na interface, use o botão "Authorize" para inserir o seu token JWT gerado na rota de login e testar os fluxos protegidos, como o Checkout.*

## ⚙️ Rodando Localmente

### Pré-requisitos
*   Docker & Docker Compose
*   Java 21 / JDK 21

### Passo a Passo
1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/yourusername/atelier.git
    cd atelier
    ```

2.  **Inicie a Infraestrutura (PostgreSQL, Redis, MinIO):**
    ```bash
    docker-compose up -d
    ```

3.  **Configure as Variáveis de Ambiente:**
    Crie um arquivo `.env` na raiz do projeto ou configure sua IDE com as seguintes chaves (valores fictícios podem ser usados no perfil dev):
    ```env
    STRIPE_API_KEY=sk_test_...
    STRIPE_WEBHOOK_SECRET=whsec_...
    JWT_SECRET=your_super_secret_key_with_at_least_256_bits
    RESEND_API_KEY=re_...
    ```

4.  **Rode a Aplicação:**
    O projeto usa Flyway, então as tabelas e os dados de teste iniciais serão gerados magicamente na primeira inicialização.
    ```bash
    ./gradlew bootRun --args='--spring.profiles.active=dev'
    ```

## 🧪 Testes Automatizados

Escrever testes é o que garante noites de sono tranquilas. O projeto possui mais de **130 testes automatizados** (Unitários e de Integração) validando praticamente todas as regras de negócio e os retornos de erro da API.

```bash
./gradlew test
```

## 📝 Licença
Este projeto é um laboratório de código aberto para fins educacionais e de portfólio. Sinta-se livre para explorar!