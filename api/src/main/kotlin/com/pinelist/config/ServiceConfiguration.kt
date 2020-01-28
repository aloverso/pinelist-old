package com.pinelist.config

import com.pinelist.domain.pinelist.ListRepository
import com.pinelist.domain.pinelist.ListService
import com.pinelist.services.DefaultListService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServiceConfiguration {

    @Bean
    fun getListService(listRepository: ListRepository): ListService {
        return DefaultListService(listRepository)
    }
}