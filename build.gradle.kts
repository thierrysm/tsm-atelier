plugins {
	java
	id("org.springframework.boot") version "4.0.2"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.diffplug.spotless") version "8.3.0"
}

group = "com.tsm"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	implementation("org.springframework.boot:spring-boot-starter-flyway")
	implementation("org.flywaydb:flyway-database-postgresql:10.10.0")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
	testImplementation("org.springframework.boot:spring-boot-starter-flyway-test")
	testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testImplementation("org.mockito:mockito-core:5.22.0")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

}

tasks.withType<Test> {
	useJUnitPlatform()
}

spotless {
	java {
		googleJavaFormat()
		removeUnusedImports()
		trimTrailingWhitespace()
		endWithNewline()
	}
}
