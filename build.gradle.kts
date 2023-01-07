import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.spring") version "1.7.10"
}

group = "com.cunningbird"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/cunningbird/sfedu-mmcs-achievements-contract")
        credentials {
            username = System.getProperty("privateRegistryUsername")
            password = System.getProperty("privateRegistryPassword")
        }
    }
}

dependencies {
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.10")
    runtimeOnly("org.jetbrains.kotlin:kotlin-reflect:1.6.10")

    // Reactive
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.7")
    runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.4")

    // Web
    implementation("io.grpc:grpc-stub:1.48.0")
    implementation("net.devh:grpc-server-spring-boot-starter:2.13.1.RELEASE")

    // Contract
    implementation("io.grpc:grpc-protobuf:1.48.0")
    implementation("com.cunningbird.sfedu:mmcs-achievements-contract:1.0.0")

    // Persistence
    implementation("org.postgresql:postgresql:42.4.3")
    implementation ("org.flywaydb:flyway-core:9.0.4")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.h2database:h2:2.1.214")
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

tasks.getByName<Jar>("jar") {
    enabled = false // TODO build thin jar
}