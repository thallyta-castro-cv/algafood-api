# Sistema de delivery de comida com Java e Spring Boot
Projeto em Andamento!

<b>Este projeto foi criado com Java 17 e Spring Framework 2.7.3</b>

# Sobre o projeto

Este projeto consiste em uma aplicação Backend com Spring Boot para criar um sistema de delivery de comida completo com todos os recursos que o Spring disponibiliza.
O intuito deste projeto é servir de repositório base para consultas futuras de implementação de recursos do framework.

# Tecnologias utilizadas


### Backend

- Java
- Spring Boot
- Spring Data JPA
- Spring Hateoas
- MySql
- jUnit 5
- Mockito
- Swagger
- OpenApi 3
- SpringFox

### Bibliotecas e plugins utilizados

- Lombok
- Squiggly (JsonFilter)
- RestAssured

# Iniciando o projeto

Para iniciar a aplicação execute a seguinte ação na pasta algafood

```shell script
mvn spring-boot:run
```

Instale as dependências do maven com o comando:

```shell script
mvn clean install
```

Consultar documentação da Api:

```shell script
http://localhost:8080/swagger-ui/index.html#/
http://localhost:8080/v3/api-docs
```
## Configuração de credenciais AWS
Este projeto utiliza recursos da AWS, como S3 (para armazenamento de arquivos) e SES (para envio de e-mails). Por questões de segurança, as chaves de acesso não podem ser incluídas diretamente no código. Portanto, é necessário que o usuário crie suas próprias chaves de acesso antes de rodar o projeto em sua máquina.
### Gerando credenciais AWS

1. Acesse o [Console da AWS](https://aws.amazon.com/console/).
2. Navegue até o serviço **IAM** (Gerenciamento de Identidade e Acesso).
3. Crie um novo **Usuário** com permissões para o S3 e SES.
4. Gere um par de **Access Key** e **Secret Key**.
5. Configure essas chaves no arquivo `application.properties` do seu projeto, substituindo as chaves de exemplo pelos valores gerados.

### Exemplo de configuração no `application.properties`:

```properties
# Configurações do Banco de Dados
spring.datasource.url=jdbc:mysql://localhost/db_algafood?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=12345678

# Flyway
spring.flyway.locations=classpath:db/migration/production

# JPA e Jackson
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jackson.deserialization.fail-on-unknown-properties=true
spring.jackson.deserialization.fail-on-ignored-properties=true

# Configurações de Armazenamento
algafood.storage.type=local
algafood.storage.local.directory-photos=src/main/java/br/com/thallyta/algafood/files
algafood.storage.s3.access-key=sua-chave-de-acesso-aqui
algafood.storage.s3.secret-key=sua-chave-secreta-aqui
algafood.storage.s3.bucket=algafood-api-test-00254
algafood.storage.s3.region=us-east-2
algafood.storage.s3.folder=catalogo

# Configurações de E-mail (AWS SES)
spring.mail.host=email-smtp.us-east-2.amazonaws.com
spring.mail.port=587
spring.mail.username=sua-chave-de-acesso-aqui
spring.mail.password=sua-chave-secreta-aqui
algafood.mail.sender=seu-email-aqui@gmail.com

# Outras Configurações
spring.mvc.throw-exception-if-no-handler-found=true
spring.mvc.pathmatch.matching-strategy=ANT_PATH_MATCHER
spring.web.resources.add-mappings=false
spring.freemarker.settings.locale=pt_BR
```

## Branches de referência - Desenvolvimento

| Branch                        | Descrição                                                                                               |
|-------------------------------|---------------------------------------------------------------------------------------------------------|
| `master`                      | Projeto completo                                                                                        |
| `feat/crud-basic`             | Implementação de um CRUD da forma mais básica.                                                          |
| `feat/crud-service`           | Implementação do CRUD com a camada de serviço.                                                          |
| `feat/crud-repository-jpa`    | Implementação de recursos do JPA e padrão repository.                                                   |
| `feat/migrations-flyway`      | Implementação do Flyway para criar as migrações.                                                        |
| `feat/custom-exception-handle`| Implementação de tratamento e modelagem de erros da API.                                                |
| `feat/include-patterns`       | Inclusão de DTO's e Mappers nos CRUD's para aplicação de boas práticas.                                 |
| `feat/advanced-api`           | Modelagem avançada de API's usando sub-recursos para relacionamento, granularidade de recursos e conceitos abstratos e não-CRUD. |
| `feat/search-reports-projections` | Implementação de pesquisas, relatórios e projeções no sistema.                                          |
| `feat/upload-download-files`  | Implementação de download e upload de arquivos com AWS S3 e armazenamento local.                        |
| `feat/send-transactional-email`| Implementação de envio de emails transacionais com AWS SES.                                             |
| `feat/api-client-js-java`     | Implementação de cache e otimizações gerais para consumo do cliente.                                    |
| `feat/implements-api-docs`    | Documentação da API com Swagger OpenAPI 3 (legado).                                                            |
| `feat/discoverability-hateoas`| Implementação de descoberta e HATEOAS no modelo HAL com Spring Hateoas                                  |
| `feat/version-api`| Implementação de versionamento de API por URI, mantendo v1 para as APIs existentes e criando v2 para o novo recurso de cozinhas, visando melhor organização e evolução das funcionalidades sem quebrar compatibilidade com a versão anterior.                                  |


## Branches de referência - Testes
| Branch               | Descrição                                                                            |
|----------------------|--------------------------------------------------------------------------------------|
| `feat/unitary-tests`  | Implementação de testes unitários com JUnit 5 e Mockito.                             |
| `feat/api-tests`      | Implementação de testes de integração e API com banco de dados de teste.             |


# Autor
<b>Thallyta Macedo Carvalho de Castro</b>

Linkedin: https://www.linkedin.com/in/thallyta-castro/

Medium: https://medium.com/@thallyta-castro-cv

email: contato@thallytacastro.com.br
