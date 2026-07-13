# Tech Challenge Backend API - Fase 2

# 🍽️ Sistema de Gestão de Restaurantes

## 📌 Sobre o Projeto
Este projeto foi desenvolvido como parte do **Tech Challenge - Fase 2** da pós-graduação em Arquitetura e Desenvolvimento Java.  
O objetivo é criar um sistema compartilhado de gestão para restaurantes, permitindo que clientes consultem informações, façam pedidos e deixem avaliações, enquanto os donos de restaurantes gerenciam suas operações.

---

## 🚀 Funcionalidades Implementadas
- **Cadastro de Tipo de Usuário**
    - CRUD para distinguir entre **Dono de Restaurante** e **Cliente**.
    - Associação de usuários ao tipo de usuário.

- **Cadastro de Restaurante**
    - CRUD completo com campos: nome, endereço, tipo de cozinha, horário de funcionamento e dono do restaurante.

- **Cadastro de Itens do Cardápio**
    - CRUD para itens vendidos no restaurante.
    - Campos: nome, descrição, preço, disponibilidade e caminho da foto.

---

## 🏗️ Arquitetura
O projeto segue os princípios de **Clean Architecture**, garantindo separação de responsabilidades e escalabilidade:

- **Domain** → Regras de negócio e entidades.
- **Application** → Casos de uso e lógica de aplicação.
- **Infrastructure** → Persistência, controllers e integração com frameworks.

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

## 🎥 Vídeo de Demonstração


## 👨‍💻 Autores

- Thiago Vanzele – RM374158
- Luiz Eduardo Gambeti – RM373030
- Sandoval Bento da Silva - RM370706

