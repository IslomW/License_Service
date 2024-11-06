FROM maven:3.8.6-amazoncorretto-17 AS bulid
COPY pom.xml /build/
WORKDIR /build/
RUN mvn dependency:go-offline
COPY src /build/src/
RUN mvn package -DskipTest

#Run stage
FROM openjdk:17-alpine

ARG JAR_FILE=/build/target/*.jar
COPY --from=bulid $JAR_FILE /opt/docker-test/app.jar
ENTRYPOINT ["java", "-jar", "/opt/docker-test/app.jar"]


# Используем официальное изображение Vault как базовое
FROM vault:latest

# Указываем рабочий каталог для Vault
WORKDIR /vault

# Копируем конфигурационный файл Vault в контейнер
COPY ./config.hcl /vault/config/config.hcl

# Экспортируем переменные среды для Vault
ENV VAULT_ADDR="http://0.0.0.0:8200"

# Команда запуска Vault с пользовательским конфигурационным файлом
CMD ["vault", "server", "-config=/vault/config/config.hcl"]