package com.pinelist.config

import com.pinelist.domain.pinelist.PineList
import com.pinelist.domain.pinelist.ListRepository
import com.pinelist.domain.pinelist.stubs.StubListRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("stub-integrations")
@Configuration
class IntegrationStubConfiguration {

    @Bean
    fun getListRepository(): ListRepository {
        val stubListRepository = StubListRepository()
        stubListRepository.stubbedPineLists = listOf(
                PineList(
                        name = "my list",
                        id = "123"
                ),
                PineList(
                        name = "my cool list",
                        id = "456"
                )
        )

        return stubListRepository
    }
}
