package com.pinelist.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfiguration: WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
                .allowedMethods("GET","POST","OPTIONS","DELETE","PUT","HEAD")
                .allowedOrigins("*")
                .allowCredentials(true)
    }
}