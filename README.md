# 📋 Divisão de Responsabilidades - Fase 2

## 👥 Integrantes

* **Integrante 1:** Sandoval Bento
* **Integrante 2:** Thiago Vanzele
* **Integrante 3:** ______________________

---

# 👤 Integrante 1 - Usuários e Tipos de Usuário

## ✅ Funcionalidades

### Tipo de Usuário

* [ ] Criar entidade `UserType`
* [ ] Criar Repository
* [ ] Criar Service
* [ ] Criar Controller
* [ ] Criar DTOs
* [ ] Implementar CRUD completo
* [ ] Criar validações
* [ ] Criar tratamento de exceções

### Associação com Usuário

* [ ] Relacionar usuário ao tipo de usuário
* [ ] Atualizar entidade `User`
* [ ] Atualizar DTOs
* [ ] Atualizar regras de cadastro

### Testes

* [ ] Criar testes unitários
* [ ] Criar testes de integração

### Documentação

* [ ] Documentar endpoints
* [ ] Criar Collection do Postman

---

# 🍽️ Integrante 2 - Restaurante

## ✅ Funcionalidades

### Cadastro de Restaurante

* [ ] Criar entidade `Restaurant`
* [ ] Criar Repository
* [ ] Criar Service
* [ ] Criar Controller
* [ ] Criar DTOs
* [ ] Implementar CRUD completo
* [ ] Criar validações

### Relacionamentos

* [ ] Associar restaurante ao usuário (dono)
* [ ] Validar existência do usuário responsável

### Testes

* [ ] Criar testes unitários
* [ ] Criar testes de integração

### Documentação

* [ ] Documentar endpoints
* [ ] Atualizar Collection do Postman

---

# 🍕 Integrante 3 - Cardápio

## ✅ Funcionalidades

### Cadastro de Itens

* [ ] Criar entidade `MenuItem`
* [ ] Criar Repository
* [ ] Criar Service
* [ ] Criar Controller
* [ ] Criar DTOs
* [ ] Implementar CRUD completo
* [ ] Criar validações

### Relacionamentos

* [ ] Associar item ao restaurante
* [ ] Validar existência do restaurante

### Campos Obrigatórios

* [ ] Nome
* [ ] Descrição
* [ ] Preço
* [ ] Disponível apenas no restaurante
* [ ] Caminho da foto

### Testes

* [ ] Criar testes unitários
* [ ] Criar testes de integração

### Documentação

* [ ] Documentar endpoints
* [ ] Atualizar Collection do Postman

---

# 🏛️ Clean Architecture

## Integrante 1

* [ ] Organizar módulo de Usuários
* [ ] Separar Domain, Application e Infrastructure

## Integrante 2

* [ ] Organizar módulo de Restaurante
* [ ] Separar Domain, Application e Infrastructure

## Integrante 3

* [ ] Organizar módulo de Cardápio
* [ ] Separar Domain, Application e Infrastructure

---

# 🧪 Testes

## Integrante 1

* [ ] Garantir cobertura do módulo de Usuários

## Integrante 2

* [ ] Garantir cobertura do módulo de Restaurante

## Integrante 3

* [ ] Garantir cobertura do módulo de Cardápio

## Todos

* [ ] Cobertura total mínima de 80%
* [ ] Revisar testes dos demais módulos

---

# 🐳 Docker

## Responsável: Qualquer um

* [ ] Criar `Dockerfile`
* [ ] Configurar `docker-compose.yml`
* [ ] Configurar banco de dados
* [ ] Validar execução da aplicação via Docker

---

# 📬 Collection Postman

## Integrante 1

* [ ] Endpoints de Usuários

## Integrante 2

* [ ] Endpoints de Restaurantes

## Integrante 3

* [ ] Endpoints de Cardápio

## Todos

* [ ] Revisar Collection completa

---

# ✅ Checklist Final

## Todos

* [ ] Código revisado
* [ ] Testes passando
* [ ] Cobertura mínima de 80%
* [ ] Docker funcionando
* [ ] README atualizado
* [ ] Collection do Postman atualizada
* [ ] Merge na branch principal
* [ ] Projeto validado para entrega
* [ ] Vídeo gravado

# 📁 Estrutura do Projeto

## 📦 Estrutura Geral

```text
src
└── main
    └── java
        └── br
            └── com
                └── restaurante
                    │
                    ├── RestaurantApplication.java
                    │
                    ├── modules
                    │   ├── user
                    │   ├── restaurant
                    │   └── menuitem
                    │
                    └── shared
                        ├── config
                        ├── exception
                        ├── security
                        └── util
```

---

# 📦 Shared

```text
shared
├── config
│   ├── BeanConfig.java
│   ├── JacksonConfig.java
│   └── OpenApiConfig.java
│
├── exception
│   ├── BusinessException.java
│   ├── ResourceNotFoundException.java
│   └── GlobalExceptionHandler.java
│
├── security
│
└── util
    ├── DateUtils.java
    └── ValidationUtils.java
```

---

# 👤 User

```text
user
├── domain
│   ├── entity
│   │   └── User.java
│   │
│   ├── enums
│   │   └── UserType.java
│   │
│   ├── gateway
│   │   └── UserGateway.java
│   │
│   └── exception
│       ├── UserAlreadyExistsException.java
│       └── UserNotFoundException.java
│
├── application
│   └── usecase
│       ├── CreateUserUseCase.java
│       ├── UpdateUserUseCase.java
│       ├── DeleteUserUseCase.java
│       ├── FindUserByIdUseCase.java
│       └── FindAllUsersUseCase.java
│
└── infrastructure
    ├── controller
    │   ├── dto
    │   │   ├── request
    │   │   │   ├── CreateUserRequest.java
    │   │   │   └── UpdateUserRequest.java
    │   │   │
    │   │   └── response
    │   │       └── UserResponse.java
    │   │
    │   ├── mapper
    │   │   └── UserMapper.java
    │   │
    │   └── UserController.java
    │
    ├── gateway
    │   └── UserGatewayImpl.java
    │
    └── persistence
        ├── entity
        │   └── UserEntity.java
        │
        ├── mapper
        │   │── UserEntityMapper.java
        │
        └── repository
            └── UserRepository.java
```

---

# 🍽️ Restaurant

```text
restaurant
├── domain
│   ├── entity
│   │   └── Restaurant.java
│   │
│   ├── gateway
│   │   └── RestaurantGateway.java
│   │
│   └── exception
│       └── RestaurantNotFoundException.java
│
├── application
│   └── usecase
│       ├── CreateRestaurantUseCase.java
│       ├── UpdateRestaurantUseCase.java
│       ├── DeleteRestaurantUseCase.java
│       ├── FindRestaurantByIdUseCase.java
│       └── FindAllRestaurantsUseCase.java
│
└── infrastructure
    ├── controller
    │   ├── dto
    │   │   ├── request
    │   │   │   ├── CreateRestaurantRequest.java
    │   │   │   └── UpdateRestaurantRequest.java
    │   │   │
    │   │   └── response
    │   │       └── RestaurantResponse.java
    │   │
    │   ├── mapper
    │   │   └── RestaurantMapper.java
    │   │
    │   └── RestaurantController.java
    │
    ├── gateway
    │   └── RestaurantGatewayImpl.java
    │
    └── persistence
        ├── entity
        │   └── RestaurantEntity.java
        │
        ├── mapper
        │   └── RestaurantEntityMapper.java
        │
        └── repository
            └── RestaurantRepository.java
```

---

# 🍕 MenuItem

```text
menuitem
├── domain
│   ├── entity
│   │   └── MenuItem.java
│   │
│   ├── gateway
│   │   └── MenuItemGateway.java
│   │
│   └── exception
│       └── MenuItemNotFoundException.java
│
├── application
│   └── usecase
│       ├── CreateMenuItemUseCase.java
│       ├── UpdateMenuItemUseCase.java
│       ├── DeleteMenuItemUseCase.java
│       ├── FindMenuItemByIdUseCase.java
│       ├── FindMenuItemsByRestaurantUseCase.java
│       └── FindAllMenuItemsUseCase.java
│
└── infrastructure
    ├── controller
    │   ├── dto
    │   │   ├── request
    │   │   │   ├── CreateMenuItemRequest.java
    │   │   │   └── UpdateMenuItemRequest.java
    │   │   │
    │   │   └── response
    │   │       └── MenuItemResponse.java
    │   │
    │   ├── mapper
    │   │   └── MenuItemMapper.java
    │   │
    │   └── MenuItemController.java
    │
    ├── gateway
    │   └── MenuItemGatewayImpl.java
    │
    └── persistence
        ├── entity
        │   └── MenuItemEntity.java
        │
        ├── mapper
        │   └── MenuItemEntityMapper.java
        │
        └── repository
            └── MenuItemRepository.java
```

---

# 🧪 Estrutura de Testes

```text
src
└── test
    └── java
        └── br
            └── com
                └── restaurante
                    ├── user
                    │   ├── application
                    │   ├── infrastructure
                    │   │   ├── controller
                    │   │   └── gateway
                    │
                    ├── restaurant
                    │   ├── application
                    │   ├── infrastructure
                    │   │   ├── controller
                    │   │   └── gateway
                    │
                    └── menuitem
                        ├── application
                        ├── infrastructure
                        │   ├── controller
                        │   └── gateway
```

---

# 🏛️ Arquitetura das Camadas

```text
Controller
    │
    ▼
Request DTO
    │
    ▼
Mapper
    │
    ▼
Domain Entity
    │
    ▼
Use Case
    │
    ▼
Gateway (Interface)
    │
    ▼
GatewayImpl
    │
    ▼
Repository
    │
    ▼
Database
```

Fluxo de retorno:

```text
Database
    │
    ▼
Repository
    │
    ▼
GatewayImpl
    │
    ▼
Domain Entity
    │
    ▼
Use Case
    │
    ▼
Mapper
    │
    ▼
Response DTO
    │
    ▼
Controller
```

---

# 📦 Modelo de Domínio

## User

```text
id
nome
email
userType (UserType)
```

## UserType

```java
ADMIN
OWNER
CUSTOMER
```

## Restaurant

```text
id
nome
endereco
tipoCozinha
horarioFuncionamento
dono (User)
```

## MenuItem

```text
id
nome
descricao
preco
somenteLocal
foto
restaurant (Restaurant)
```
