# API de ocorrências

> OBS: o deploy foi feito em um servidor gratuito, ao clicar no link da aplicação, aguarde 1min para que o servidor saia do stand by e a aplicação seja iniciada. As imagens enviadas à API permanecem nela somente durante esta execução, ao entrar em stand by novamente elas serão perdidas

<a href="https://ocorrencia-api.onrender.com/swagger-ui/index.html" target="_blank">Acesse a Aplicação</a>

<a href="https://www.linkedin.com/in/erique-rocha-dev/" target="_blank">LinkedIn</a>

#### Autor
[Erique Rocha](https://github.com/EriqueRocha)

## Apresentação:
Esta aplicação tem como objetivo implementar um sistema de cadastro de ocorrências de alagamento em determinada cidade/bairro, isto foi feito da seguinte forma: teremos perfis de usuários e administradores. Usuários podem cadastrar ocorrencias em seu nome, editar suas ocorrencias e vizualizar ocorrencias feitas por outros usuários, administradores podem excluir e editar ocorrências e usuários livremente, também podem executar as mesmas funçoes de um usuário. As ocorrências vão conter endereço, intensidade, classificação e duas fotos como dados a serem inseridos pelo usuário.

### Tecnologias:
* Java 1.17+
* SpringBoot 2.7.4
* Spring Security
* JWT
* Password Encoder
* PostgreSQL
* Autoridades
* SpringDataJpa
* Hibernate
* SpringWeb
* SpringTest
* Projeto Lombok
* Swagger OpenAPI
* Global Exception Handlers
* PostgreSQL
* PGAdmin
* Flyway

### Autenticação:
Neste caso, a Autenticação para provar quem é o usuário é feita por login(email) e senha

### Autoridade (Roles):
Define a qual grupo o perfil pretence, neste caso, usuário e administrador e cada perfil terásuas autorizações de acordo com seu grupo

Grupos/perfis utilizados:
```
username = user, senha = UserSenha, role = USER

username = admin, senha = ManagerSenha, role = MANAGER
```

>Ver migration `V01_02__insert_data_tab_usuario.sql`

### Dependências:
```XML
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
	<groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
	<groupId>org.flywaydb</groupId>
	<artifactId>flyway-core</artifactId>
</dependency>

<dependency>
	<groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
	<scope>runtime</scope>
</dependency>

<dependency>
	<groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
	<optional>true</optional>
</dependency>

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-test</artifactId>
	<scope>test</scope>
</dependency>

<dependency>
	<groupId>org.springdoc</groupId>
	<artifactId>springdoc-openapi-ui</artifactId>
	<version>1.6.4</version>
</dependency>

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-validation</artifactId>
</dependency>

<dependency>
	<groupId>commons-io</groupId>
	<artifactId>commons-io</artifactId>
	<version>2.8.0</version>
</dependency>

<dependency>
  <groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-security</artifactId>
</dependency>

<dependency>
	<groupId>io.jsonwebtoken</groupId>
	<artifactId>jjwt</artifactId>
	<version>0.7.0</version>
</dependency>
```

### SpringDataJpa:
```XML
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```
Implementa uma camada de acesso aos dados de forma facilitada, faça seus métodos personalizados e o Spring fornecerá a implementação

### Flyway:
```XML
<dependency>
	<groupId>org.flywaydb</groupId>
	<artifactId>flyway-core</artifactId>
</dependency>
```
ajuda com a migração de banco de dados, no caso, está sendo utilizado para que a aplicação inicie com um administrador e um usuário cadastrado

### PostgreSQL:
```XML
<dependency>
	<groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
	<scope>runtime</scope>
</dependency>
```
drive para a utilização do SGBD PostgreSQL

### Lombok:
```XML
<dependency>
	<groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
	<optional>true</optional>
</dependency>
```
biblioteca voltada a produtividade e evitar boilerplate, com o uso de anotações criamos sem a necessidade de digitar de forma extensa, por exemplo, getters, setters e construtores

### Springdoc openapi ui:
```XML
<dependency>
	<groupId>org.springdoc</groupId>
	<artifactId>springdoc-openapi-ui</artifactId>
	<version>1.6.4</version>
</dependency>
```
biblioteca que ajuda a automatizar a geração de documentação da API usando projetos de inicialização do spring

### Spring validation:
```XML
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```
uma especificação Java que tem a finalidade de lidar com as validações de dados de um modo centralizado, tendo em vista que elas são integradas ao código a partir de anotações

### commons io:
```XML
<dependency>
	<groupId>commons-io</groupId>
	<artifactId>commons-io</artifactId>
	<version>2.8.0</version>
</dependency>
```
A biblioteca Apache Commons IO contém classes de utilitários, implementações de fluxo, filtros de arquivos, Comparadores de arquivos e muito mais

### Spring security:
```XML
<dependency>
  <groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
O Spring Security é uma estrutura que fornece autenticação, autorização e proteção. Com suporte de primeira classe para proteger aplicativos imperativos e reativos, é o padrão de proteção para aplicativos baseados em Spring

### Json web token:
```XML
<dependency>
	<groupId>io.jsonwebtoken</groupId>
	<artifactId>jjwt</artifactId>
	<version>0.7.0</version>
</dependency>
```
JSON Web Token (JWT) é um padrão que define uma maneira compacta e independente para transmitir informações com segurança entre as partes como um objeto JSON

### o Básico para começar a utilizar a aplicação pelo o swagger:
[link da aplicação](https://ocorrencia-api.onrender.com/swagger-ui/index.html)

<img src="https://github.com/EriqueRocha/thm-ocorrencias/blob/master/imagem/end%20point%201.png" style="width: 50%;">

rolando para baixo você vai encontrar o endpoint de login do tipo POST

<img src="https://github.com/EriqueRocha/thm-ocorrencias/blob/master/imagem/experimente.png" style="width: 50%;">

onde você vai clicar em "try it out". e poderar executar um método POST que por padrão já está vindo com um administrador preenchido. basta executar.

<img src="https://github.com/EriqueRocha/thm-ocorrencias/blob/master/imagem/login.png" style="width: 50%;">

Após isto, se o login for bem sucediso, a API retorna alguns dados deste administrador e seu token, o token deve ser copiado para fazer a autorização das suas rotas

<img src="https://github.com/EriqueRocha/thm-ocorrencias/blob/master/imagem/obtendo%20token.png" style="width: 50%;">

volte ao topo da página e clique em authorize

<img src="https://github.com/EriqueRocha/thm-ocorrencias/blob/master/imagem/tela%20inicial.png" style="width: 50%;">

e cole o token que havia copiado depois clique em authorize e pode fechar esta janela

<img src="https://github.com/EriqueRocha/thm-ocorrencias/blob/master/imagem/colando%20token.png" style="width: 50%;">

agora todas as rotas estão habilitadas para serem utilizadas no swagger


| HTTP                              | Descrição                                           |
|-----------------------------------|-----------------------------------------------------|
| PUT:/usuarios/id                  | Edita um usuário                                    |
| POST:/usuarios/cadastro           | Cadastra um usuário                                 |
| GET:/usuarios/{id}                | Busca um usuário                                    |
| DELETE:/usuarios/{id}             | deleta um usuário                                   |
| GET:/usuarios/list                | Lista todos os usuarios na base de dados            |
| PUT:/ocorrencias/id               | edita uma ocorrência                                |
| POST:/ocorrencias                 | adiciona uma ocorrência                             |
| GET:/ocorrencias/{id}             | busca uma ocorrencia especifica                     |
| DELETE:/ocorrencias/{id}          | deleta uma ocorrência                               |
| GET:/ocorrencias/userId/{id}      | busca as ocorrências de um usuário                  |

### Arquivo usado para configuração do docker:
este Arquivo é usado para criar a imagem Docker
>Ver `Dockerfile`
```Dockerfile
FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk maven -y
COPY . .

RUN mvn package

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /target/thm-ocorrencias-1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
```
