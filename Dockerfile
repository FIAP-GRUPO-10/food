# ================= STAGE 1: Build =================
FROM maven:3.9-eclipse-temurin-21 AS build

# Diretório de trabalho
WORKDIR /app

# Copia o pom.xml primeiro para aproveitar cache
COPY pom.xml .

# Baixa dependências
RUN mvn dependency:go-offline

# Copia o código fonte
COPY src ./src

# Compila e gera o .jar
RUN mvn clean package -DskipTests

# ================= STAGE 2: Runtime =================
FROM amazoncorretto:21-alpine

# Diretório de trabalho
WORKDIR /app

# Copia apenas o jar gerado no estágio de build
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta correta (8080)
EXPOSE 8080

# Executa a aplicação
ENTRYPOINT ["java","-jar","app.jar"]
