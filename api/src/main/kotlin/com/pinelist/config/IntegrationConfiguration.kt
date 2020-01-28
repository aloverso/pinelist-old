package com.pinelist.config

import com.pinelist.domain.pinelist.ListRepository
import com.pinelist.integrations.DefaultListRepository
import com.pinelist.integrations.JpaListRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.boot.web.client.RestTemplateBuilder

@Configuration
class IntegrationConfiguration {

    @Bean
    fun getListRepository(jpaListRepository: JpaListRepository): ListRepository {
        return DefaultListRepository(jpaListRepository)
    }
}