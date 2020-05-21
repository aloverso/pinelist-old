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
	implementation(kotlin("reflect"))
	implementation(kotlin("stdlib-jdk8"))

	implementation("io.arrow-kt:arrow-core:0.10.3")

	implementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}
