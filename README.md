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

## 📦 Shared

```text
shared
├── config
│   ├── OpenApiConfig.java
│   ├── JacksonConfig.java
│   └── BeanConfig.java
│
├── exception
│   ├── BusinessException.java
│   ├── ResourceNotFoundException.java
│   ├── ValidationException.java
│   └── GlobalExceptionHandler.java
│
├── mapper
│   └── MapperConfig.java
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
│   └── User.java
│
├── application
│   ├── UserService.java
│   └── UserMapper.java
│
├── infrastructure
│   ├── controller
│   │   └── UserController.java
│   │
│   └── repository
│       └── UserRepository.java
│
└── dto
    ├── request
    │   ├── CreateUserDTO.java
    │   └── UpdateUserDTO.java
    │
    └── response
        └── UserResponseDTO.java
```

---

# 👥 UserType

```text
usertype
├── domain
│   └── UserType.java
│
├── application
│   ├── UserTypeService.java
│   └── UserTypeMapper.java
│
├── infrastructure
│   ├── controller
│   │   └── UserTypeController.java
│   │
│   └── repository
│       └── UserTypeRepository.java
│
└── dto
    ├── request
    │   ├── CreateUserTypeDTO.java
    │   └── UpdateUserTypeDTO.java
    │
    └── response
        └── UserTypeResponseDTO.java
```

---

# 🍽️ Restaurant

```text
restaurant
├── domain
│   └── Restaurant.java
│
├── application
│   ├── RestaurantService.java
│   └── RestaurantMapper.java
│
├── infrastructure
│   ├── controller
│   │   └── RestaurantController.java
│   │
│   └── repository
│       └── RestaurantRepository.java
│
└── dto
    ├── request
    │   ├── CreateRestaurantDTO.java
    │   └── UpdateRestaurantDTO.java
    │
    └── response
        └── RestaurantResponseDTO.java
```

---

# 🍕 MenuItem

```text
menuitem
├── domain
│   └── MenuItem.java
│
├── application
│   ├── MenuItemService.java
│   └── MenuItemMapper.java
│
├── infrastructure
│   ├── controller
│   │   └── MenuItemController.java
│   │
│   └── repository
│       └── MenuItemRepository.java
│
└── dto
    ├── request
    │   ├── CreateMenuItemDTO.java
    │   └── UpdateMenuItemDTO.java
    │
    └── response
        └── MenuItemResponseDTO.java
```

---

# 🧪 Estrutura de Testes

```text
src
└── test
    └── java
        ├── user
        │   ├── UserServiceTest.java
        │   ├── UserControllerTest.java
        │   └── UserRepositoryTest.java
        │
        ├── usertype
        │   ├── UserTypeServiceTest.java
        │   ├── UserTypeControllerTest.java
        │   └── UserTypeRepositoryTest.java
        │
        ├── restaurant
        │   ├── RestaurantServiceTest.java
        │   ├── RestaurantControllerTest.java
        │   └── RestaurantRepositoryTest.java
        │
        └── menuitem
            ├── MenuItemServiceTest.java
            ├── MenuItemControllerTest.java
            └── MenuItemRepositoryTest.java
```

# Possível modelagem dos dados

```text
TipoUsuario
│
├── id
└── nome

Usuario
│
├── id
├── nome
├── email
└── userType

Restaurante
│
├── id
├── nome
├── endereço
├── tipoCozinha
├── horário
└── dono(Usuario)

MenuItem
│
├── id
├── nome
├── descrição
├── preço
├── somenteLocal
├── foto(url para foto)
└── restaurante

```
