# 🍽️ Sistema de Gestão de Restaurantes

## 📌 Sobre o Projeto

Este projeto foi desenvolvido como parte do **Tech Challenge – Fase 2** da pós-graduação em **Arquitetura e Desenvolvimento Java** da FIAP.

O desafio propõe o desenvolvimento de uma **API REST** para um sistema compartilhado de gestão de restaurantes. A ideia é permitir que diversos estabelecimentos utilizem uma única plataforma para administrar suas operações, reduzindo custos de desenvolvimento e manutenção de sistemas individuais.

Nesta segunda fase, o sistema foi expandido para contemplar o gerenciamento dos principais cadastros necessários para a operação dos restaurantes, utilizando boas práticas de desenvolvimento com **Java**, **Spring Boot** e **Clean Architecture**.

Além das funcionalidades de negócio, o projeto também prioriza aspectos como organização do código, documentação da API, testes automatizados e execução da aplicação em ambiente containerizado com Docker.

---

## 🎯 Objetivos da Fase 2

Nesta etapa foram implementadas as seguintes funcionalidades:

- Gerenciamento de **Tipos de Usuário**, permitindo diferenciar **Clientes** e **Donos de Restaurante**.
- Cadastro completo de **Restaurantes**, vinculando cada estabelecimento ao seu respectivo proprietário.
- Cadastro e gerenciamento dos **Itens do Cardápio**, contendo informações como descrição, preço, disponibilidade e caminho da imagem do prato.

Além das funcionalidades, o projeto atende aos requisitos técnicos da fase:

- Arquitetura baseada em **Clean Architecture**.
- API documentada com **OpenAPI/Swagger**.
- Testes automatizados.
- Containerização utilizando **Docker** e **Docker Compose**.
- Disponibilização de Collection para testes da API.
- Código organizado seguindo boas práticas de desenvolvimento com Spring Boot.

---

## 🚀 Funcionalidades Implementadas

### 👤 Cadastro de Tipo de Usuário

- CRUD completo de tipos de usuário.
- Diferenciação entre **Cliente** e **Dono de Restaurante**.
- Associação de usuários aos respectivos tipos.

### 🏪 Cadastro de Restaurantes

- CRUD completo de restaurantes.
- Cadastro de:
  - Nome;
  - Endereço;
  - Tipo de cozinha;
  - Horário de funcionamento;
  - Dono do restaurante.

### 🍔 Cadastro de Itens do Cardápio

- CRUD completo dos itens do cardápio.
- Cadastro de:
  - Nome;
  - Descrição;
  - Preço;
  - Disponibilidade;
  - Caminho da imagem do prato.

---

## 🏗️ Arquitetura

O projeto foi desenvolvido seguindo os princípios da **Clean Architecture**, separando as responsabilidades em camadas independentes. Essa abordagem mantém as regras de negócio desacopladas dos detalhes de implementação, facilitando a manutenção, os testes e a evolução da aplicação.

As dependências seguem a regra da Clean Architecture: **as camadas externas dependem das internas**, enquanto o núcleo da aplicação permanece independente de frameworks, banco de dados e outras tecnologias.

```text
Módulo
├── application
│   ├── config
│   └── usecase
├── domain
│   ├── entity
│   ├── exception
│   └── gateway
└── infrastructure
    ├── controller
    ├── gateway
    └── persistence
````

### 📌 Domain

É o núcleo da aplicação, onde estão concentradas as regras de negócio.

* **entity** → Entidades do domínio e seus comportamentos.
* **exception** → Exceções relacionadas às regras de negócio.
* **gateway** → Interfaces (contratos) utilizadas pelo domínio para comunicação com recursos externos.

Essa camada **não possui dependência** de frameworks, banco de dados ou qualquer tecnologia específica.

### 📌 Application

Responsável por coordenar os casos de uso da aplicação.

* **usecase** → Implementação dos casos de uso, aplicando as regras de negócio e utilizando os contratos definidos no domínio.
* **config** → Configurações da camada de aplicação.

A camada de aplicação conhece apenas o domínio e seus contratos, sem depender de detalhes de infraestrutura.

### 📌 Infrastructure

Responsável pelos detalhes de implementação e integração com tecnologias externas.

* **controller** → Endpoints REST responsáveis por receber e responder às requisições HTTP.
* **gateway** → Implementações dos contratos definidos no domínio.
* **persistence** → Entidades de persistência, repositórios, mapeamentos e acesso ao banco de dados.

É a única camada que conhece frameworks como Spring Boot, JPA e demais componentes externos.

### 🔄 Fluxo de uma requisição

Uma requisição percorre as camadas na seguinte ordem:

```text
Controller
    ↓
Use Case
    ↓
Gateway (Interface - Domain)
    ↓
Gateway (Implementação - Infrastructure)
    ↓
Persistence (Banco de Dados)
```

A resposta retorna pelo caminho inverso até o controlador, que envia o resultado ao cliente.

Essa organização proporciona:

* ✅ Baixo acoplamento entre as camadas.
* ✅ Alta coesão das responsabilidades.
* ✅ Facilidade para realização de testes unitários.
* ✅ Independência de frameworks e banco de dados.
* ✅ Maior facilidade para manutenção e evolução da aplicação.

---

## 📂 Estrutura do Repositório


---

## 🔗 Endpoints da API
### Tipo de Usuários
- `POST /api/v1/tipo-usuario` → Criar tipo de usuário
- `GET /api/v1/tipo-usuario` → Listar tipos de usuário
- `PUT /api/v1/tipo-usuario/{id}` → Atualizar tipo de usuário
- `DELETE /api/v1/tipo-usuario/{id}` → Remover tipo de usuário

### Usuários
- `POST /api/v1/usuario` → Criar usuário
- `GET /api/v1/usuario` → Listar usuários
- `PUT /api/v1/usuario/{id}` → Atualizar usuário
- `PATCH /api/v1/usuario/{id}` → Atualizar o tipo do usuário de um usuário
- `DELETE /api/v1/usuario/{id}` → Remover usuário

### Restaurantes
- `POST /api/v1/restaurante` → Criar restaurante
- `GET /api/v1/restaurante/{id}` → Busca restaurante por id
- `GET /api/v1/restaurante` → Listar restaurantes
- `PUT /api/v1/restaurante/{id}` → Atualizar restaurante
- `DELETE /api/v1/restaurante/{id}` → Remover restaurante

### Item do Cardápio
- `POST /api/v1/item-cardapio` → Criar item de cardápio
- `GET /api/v1/item-cardapio` → Listar itens do cardápio
- `PUT /api/v1/item-cardapio/{id}` → Atualizar item do cardápio
- `DELETE /api/v1/item-cardapio/{id}` → Remover item do cardápio

---

## 🧪 Testes
- **Cobertura mínima de 80%** com testes unitários.
- Testes de integração para validar comunicação entre módulos.

---

## 🐳 Docker Compose
O projeto inclui um `docker-compose.yml` para subir:
- Aplicação Java (Spring Boot).
- Banco de dados (SQL, conforme configuração).

```bash
docker-compose up -d
```

## 📖 Documentação da API (Swagger)

Após iniciar a aplicação, a documentação interativa da API estará disponível pelo **Swagger UI**.

**Acesse em:**

http://localhost:8080/swagger-ui/index.html

> **Observação:** certifique-se de que a aplicação esteja em execução na porta **8080** antes de acessar a documentação.

---

## 🎥 Vídeo de Demonstração

O vídeo apresenta a execução da aplicação, demonstrando as funcionalidades implementadas nesta fase do projeto:

➡️ [Assistir ao vídeo](https://drive.google.com/file/d/1ZCRvv1lDLGgfXYNC4TQ1BO2sAIgAUBoP/view?usp=drive_link)

---

## 👨‍💻 Autores

- Thiago Vanzele – RM374158
- Luiz Eduardo Gambeti – RM373030
- Sandoval Bento da Silva - RM370706

