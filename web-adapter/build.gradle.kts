plugins {
	kotlin("jvm")
	kotlin("plugin.spring")
}

dependencyManagement {
	imports {
		mavenBom(
				org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES
		)
	}
}

dependencies {
	implementation(project(":list-policy"))

	implementation(kotlin("reflect"))
	implementation(kotlin("stdlib-jdk8"))
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}