package Grupo4.Sistema.AgroTech.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Configuración limpia sin interceptores conflictivos
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Habilita el acceso a recursos estáticos si los necesitas
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}