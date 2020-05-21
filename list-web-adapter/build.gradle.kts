plugins {
	kotlin("jvm")
	kotlin("plugin.spring")
	id("org.springframework.cloud.contract") version "2.2.2.RELEASE"
}

dependencyManagement {
	imports {
		mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
	}
}

dependencies {
	implementation(project(":list-policy"))

	implementation("io.arrow-kt:arrow-core:0.10.3")

	implementation(kotlin("reflect"))
	implementation(kotlin("stdlib-jdk8"))

	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("io.rest-assured:spring-web-test-client")
	testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier:2.2.2.RELEASE")
}

contracts {
	baseClassForTests.set("com.pinelist.webadapter.ContractsBaseTest")
}