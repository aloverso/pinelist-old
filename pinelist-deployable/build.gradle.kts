plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
	kotlin("plugin.spring")
}

tasks.register<Copy>("copyNpmBuild") {
	dependsOn(":web:build")
	from("${projectDir}/../web/build")
	into("src/main/resources/static")
}

tasks.assemble {
	dependsOn("copyNpmBuild")
	mustRunAfter("copyNpmBuild")
}

tasks.clean {
	delete(fileTree("src/main/resources/static/"))
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(project(":list-web-adapter"))
	implementation(project(":list-policy"))
	implementation(project(":list-sql-adapter"))

	implementation("io.arrow-kt:arrow-core:0.10.3")

	implementation(kotlin("reflect"))
	implementation(kotlin("stdlib-jdk8"))
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	implementation("org.postgresql:postgresql")
	implementation("org.flywaydb:flyway-core")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
