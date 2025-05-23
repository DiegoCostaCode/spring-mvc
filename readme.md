# **🎬 Spring MVC - Gerenciador de Filmes**

<details>
  <summary>CP 2 - Trabalho de QA - integrantes & vídeo</summary>

- Olá, professor. Segue os integrantes do grupo:
  - Diego Costa Silva - RM 552648
  - Mauricio Vieira Pereira - RM 553748
  - Lucas Minozzo Bronzeri - RM 553745

Executando os 3 tipos de teste em [vídeo](https://youtu.be/AnNCpFOD0Q4)

</details>

## **📖 Descrição do Projeto**

Este projeto é um exemplo de aplicação **Spring MVC**, desenvolvido durante as aulas de Java. Ele utiliza o padrão **MVC (Model-View-Controller)** para separar a lógica de negócios da apresentação, garantindo flexibilidade e facilidade de manutenção.

Além disso, o projeto disponibiliza **endpoints RESTful** para manipulação de dados de filmes, permitindo realizar operações como:

- ✏️ **Cadastro**
- ✂️ **Edição**
- 🗑️ **Exclusão**
- 📜 **Listagem de filmes**

O sistema também permite a criação de categorias de filmes.

---

## 🏗️ **Estrutura do Projeto**

### **Classes e Atributos**

####  **Filme**

A classe principal, que representa um filme no sistema.

```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "filmes")
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "diretor")
    private String diretor;
    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @Column(name = "streaming")
    private String streaming;
}
```

📂 **Arquitetura:**
- 📁 `/controller/FilmeController.java`
- 📁 `/service/FilmeService.java`
- 📁 `/repository/FilmeRepository.java`
- 📁 `/dto/FilmeRequest.java`
- 📁 `/dto/FilmeResponse.java`

---

**Categoria**

Colocamos os atributos de categoria como `ENUM` para garantir que os valores sejam fixos e conhecidos em tempo de compilação. Isso ajuda a evitar erros de digitação e garante a integridade dos dados.

```java
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Categoria {
    ROMANCE("Romance"),
    FICCAO("Ficção"),
    TERROR("Terror"),
    DRAMA("Drama"),
    ACAO("Ação");

    private String descricao;
}
```
---

## 🎮 **Controller**

### 📸 Endpoints da Web (Model and View)

- 🔍**GET** `/filme`
    - Descrição: Exibe o formulário para criar um novo filme.

- 🔍**GET** `/filme/list`
    - Descrição: Exibe a lista de todos os filmes.

- 🔍**GET** `/filme/edit/{id}`
    - Descrição: Exibe o formulário para editar um filme existente.

- 🔍**GET** `/filme/delete/{id}`
    - Descrição: Deleta um filme pelo ID e redireciona para a lista de filmes.
---
### 🧾 Endpoints da API (JSON)

- 🔍**GET** `/filme/api/`
    - Descrição: Retorna a lista de todos os filmes em formato JSON.

    - ✉️**POST** `/filme/api/`
      - Descrição: Cria um novo filme a partir dos dados fornecidos em JSON.
      - Ex.:
       ```json
      {
        "titulo": "O Senhor dos Anéis",
        "diretor": "Peter Jackson",
        "categoria": "FICCAO",
        "streaming": "Amazon Prime"
      }
      ```

- 🛠️**PUT** `/filme/api/{id}`
  - Descrição: Atualiza os dados de um filme específico pelo ID a partir dos dados fornecidos em JSON.
  - Ex.:
    ```json
    {
        "titulo": "O Senhor dos Anéis - Edição Estendida",
        "diretor": "Peter Jackson",
        "categoria": "FICCAO",
        "streaming": "Amazon Prime"
    }
    ```
  
- 🔍🆔 **GET** `/filme/api/{id}`
    - Descrição: Retorna os detalhes de um filme específico pelo ID em formato JSON.

- 🗑️**DELETE** `/filme/api/{id}`
    - Descrição: Deleta um filme específico pelo ID.


---

## 🧩 **Dependências**

Essa aplicação foi criada em **Java 21**.
```java
    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21);
        }
    }
```

E utilizamos as seguintes dependências:


**Lombok** (para geração automática de código), **Spring Boot** (configuração simplificada), **Spring Data JPA** (acesso a dados), **H2 Database** (banco em memória), **Thymeleaf** (renderização de views) e **Webjars** (gerenciamento de dependências front-end como Bootstrap).

```java
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'org.webjars:bootstrap:5.3.3'
	compileOnly 'org.projectlombok:lombok:1.18.36'
	annotationProcessor 'org.projectlombok:lombok:1.18.36'
	runtimeOnly 'com.h2database:h2'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
```

---
## 🧪 Testes

### 👨‍🚀 **Testes de API - Postman**

Utilizamos a sessão de scripts do Postman para testar as respostas da API. Contém testes positivos e negativos de todos endpoints API da aplicação (GET, POST, PUT, DELETE).

**Importe o arquivo `doc/SpringMVC.postman_collection.json` para o Postman e execute os testes.**

![img.png](src/main/java/br/com/fiap/spring_mvc/doc/img.png)

### 🚀 **Testes de Performance com JMeter**

Contém três arquivos `.jmx` com cenários distintos de performance no diretório `src/test/jmeter` do projeto testando a API RESTful do projeto de Gerenciamento de Filmes (`/filme/api`).

#### ✅ Cenário 1 – GET Massivo (`GET Massivo TEST.jmx`)

**Objetivo:** Avaliar a performance do endpoint de listagem de filmes sob carga constante de leitura.

- **Endpoint testado:** `GET /filme/api/`
- **Usuários Virtuais:** 50
- **Ramp-up:** 10 segundos
- **Duração:** 1 minuto
- **Requisições simultâneas:** sim, com sobreposição de execução
- **Validação:** Sucesso esperado com código `200`
- **Listeners recomendados:** View Results Tree, Summary Report, Aggregate Report

**Métricas observadas:**
- Tempo médio de resposta
- Pico de throughput (requisições/segundo)
- Taxa de erro próxima de 0%

**Interpretação esperada:**  
Esse teste demonstra a estabilidade da API em cenários com múltiplos usuários acessando simultaneamente a listagem de filmes, sem alterações no banco de dados.
---

#### ✅ Cenário 2 – CRUD Completo (`CRUD Completo TEST.jmx`)
**Objetivo:** Testar a estabilidade da API realizando uma sequência de operações completas por usuário.

- **Fluxo por usuário:**
  1. `POST /filme/api/` → cria filme
  2. `GET /filme/api/` → lista todos
  3. `PUT /filme/api/{id}` → atualiza
  4. `DELETE /filme/api/{id}` → remove

- **Usuários Virtuais:** 30
- **Ramp-up:** 10 segundos
- **Duração:** execução única por usuário
- **Dependência entre requisições:** uso de `JSON Extractor` para reaproveitar o ID do `POST`
- **Métricas observadas:**
  - Comportamento funcional sob carga
  - Respostas esperadas: `200`, `204` (essas são aceitas como sucesso)

---

#### ✅ Cenário 3 – POST Massivo (`POST Massivo TEST.jmx`)
**Objetivo:** Testar a capacidade da API para lidar com grandes volumes de inserções simultâneas.

- **Endpoint:** `POST /filme/api/`
- **Usuários:** 500
- **Ramp-up:** 10 segundos
- **Duração:** 60 segundos
- **Corpo da requisição:** JSON com dados fixos representando um filme genérico
- **Validação de resposta:** `201`
  **Foco do teste:**
    - Tempo de resposta médio durante carga contínua
    - Capacidade do sistema de registrar novos filmes sob pressão
    - Estabilidade e ausência de falhas de escrita
---

## 📌 Como executar os testes
1. Abrir o `.jmx` no Apache JMeter
2. Ajustar o servidor se necessário (`localhost:8080`)
3. Adicionar listeners como:
  - **View Results Tree**
  - **Summary Report**
  - **Aggregate Report**
4. Executar (botão verde ▶️)

---

### 🧪 Testes Automatizados com Selenium

Testes de interface automatizados utilizando Selenium WebDriver com JUnit, simulando interações reais do usuário com a aplicação web de gerenciamento de filmes.

📂 Localização dos testes:
```
src/
 ├── main/
 │   └── java/...
 └── test/
     └── java/
         └── br/
             └── com/
                 └── fiap/
                     └── spring_mvc/
                         └── testeSelenium.java
```
---  

# 🧪 Testes Implementados:

## ✅ `testCreateFilmeComSucesso()`  
Simula o cadastro de um novo filme preenchendo todos os campos obrigatórios.  
- Verifica se o redirecionamento para a listagem ocorre após o cadastro.

## ✅ `testUpdateFilme()`  
Simula a edição de um filme já cadastrado.  
- Acessa a listagem, abre o modal de detalhes, clica em editar, altera os campos e salva.  
- Verifica se retorna à listagem com sucesso.

## ✅ `testDeleteFilme()`  
Simula a exclusão de um filme existente.  
- Acessa a listagem, abre o modal de detalhes e clica em "Deletar".  
- Verifica se retorna à listagem corretamente.

## ✅ `testCadastroFilmeCamposVazios()`  
Verifica o comportamento ao tentar submeter o formulário de cadastro com todos os campos vazios.  
- Esperado: continuar na tela de cadastro (`/filme`) sem redirecionamento.
---  

## ▶️ Como Executar

1. Inicie o projeto localmente em `http://localhost:8080`
2. Garanta que o banco esteja limpo (recomeça vazio com o H2)
3. Execute os testes com botão direito no arquivo `testeSelenium.java` → **Run**
