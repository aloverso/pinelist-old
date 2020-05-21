package com.pinelist.sqladapter

import com.pinelist.listpolicy.ports.PinelistRepository
import com.pinelist.listpolicy.ports.contracts.PinelistRepositoryTest
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest(classes = [ SqlRepositoryTestConfig::class ])
@ExtendWith(SpringExtension::class)
@TestPropertySource(properties = [
    "spring.datasource.url=jdbc:h2:mem:~/test;MODE=PostgreSQL",
    "spring.jpa.generate-ddl=false",
    "spring.jpa.hibernate.ddl-auto=none"

])
class SqlPinelistRepositoryTest: PinelistRepositoryTest() {

    @Autowired
    private lateinit var sqlRepo: SqlPinelistRepository

    override fun getPinelistRepository(): PinelistRepository {
        return sqlRepo
    }

}

@Configuration
@EnableAutoConfiguration
@EntityScan("com.pinelist.sqladapter")
@EnableJpaRepositories
@Import(
        SqlPinelistRepository::class,
        PinelistEntity::class
)
class SqlRepositoryTestConfig
