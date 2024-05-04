package application

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
open class CorsConfiguration : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**") // Разрешить запросы ко всем endpoints
            .allowedOrigins("*") // Разрешить запросы со всех источников (для разработки)
            .allowedMethods("*") // Разрешить все методы HTTP
    }
}