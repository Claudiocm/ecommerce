import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

var swagger = "2.0.4"
var webMvc = "6.0.18"
var jupiterApi = "5.8.2"
var mockk = "1.12.0"
var containerVersion ="1.19.7"

plugins {
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    id("io.freefair.lombok") version "8.6"
}

group = "com.claudio.gobots.ecommerce"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral {
        url = uri("https://plugins.gradle.org/m2/")
    }
}

apply(plugin = "io.freefair.lombok")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.kafka:spring-kafka")
    // https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${swagger}")

    // https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-kotlin
    //runtimeOnly("org.springdoc:springdoc-openapi-kotlin:1.8.0")

    // monitoramento
    implementation("io.micrometer:micrometer-registry-datadog")

    testImplementation("org.springframework.boot:spring-boot-starter-test"){
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api:${jupiterApi}")
    // MockK
    testImplementation("io.mockk:mockk:${mockk}")
    testImplementation("org.testcontainers:testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:kafka")
    testImplementation("org.testcontainers:mongodb")
    testImplementation("org.testcontainers:mysql")
    testImplementation("org.springframework.kafka:spring-kafka-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

sourceSets {
    test {
        kotlin {
            srcDir("src/test/kotlin")

            // explicitly include or exclude tests
            include("com.claudio.gobots.ecommerce.controller/**")
            exclude("com.claudio.gobots.ecommerce/config/**")
        }
    }
}




