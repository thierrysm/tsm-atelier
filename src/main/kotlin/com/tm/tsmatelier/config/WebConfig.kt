package com.tm.tsmatelier.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry
            .addMapping("/**") // Aplica a configuração a todos os endpoints da API
            // ✨ Permite requisições vindas do seu frontend Next.js em desenvolvimento ✨
            .allowedOrigins("http://localhost:3000")
            // Permite os métodos HTTP mais comuns
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            // Permite todos os cabeçalhos na requisição
            .allowedHeaders("*")
            // ✨ Essencial para que o navegador envie o cookie do refresh token ✨
            .allowCredentials(true)
    }
}
