# Usa a imagem base do OpenJDK 17 da Zulu
FROM azul/zulu-openjdk:17 AS build

# Define o diretório de trabalho como /app
WORKDIR /ecommerce-app

# Copia o arquivo build.gradle e settings.gradle para o diretório de trabalho
COPY build.gradle .
COPY settings.gradle .

# Copia o diretório src para o diretório de trabalho
COPY src src

# Executa o build do projeto com o Gradle
RUN ./gradlew build

# Configura uma nova imagem para a execução da aplicação
FROM azul/zulu-openjdk:17

# Define o diretório de trabalho como /app
WORKDIR /ecommerce-app

# Copia o JAR da aplicação da imagem de compilação para a imagem de execução
COPY /ecommerce-app/build/libs/*.jar ecommerce.jar

# Expõe a porta 8080 para acesso à aplicação
EXPOSE 8080

# Comando para iniciar a aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "ecommerce.jar"]
