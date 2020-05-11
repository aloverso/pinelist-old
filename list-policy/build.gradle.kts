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

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}
