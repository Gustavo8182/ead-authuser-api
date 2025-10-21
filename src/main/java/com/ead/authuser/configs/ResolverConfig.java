package com.ead.authuser.configs;

//a biblioteca net.kaczmarzyk.spring.data.jpa em conjunto com as Spring Data JPA Specifications para criar consultas complexas e dinâmicas no banco de dados.
import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;
@Configuration
//essa classe configura os resolvedores de argumentos personalizados para suportar especificações dinâmicas e paginação em controladores Spring MVC.
public class ResolverConfig extends WebMvcConfigurationSupport {
        @Override
        protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolver) {
            argumentResolver.add(new SpecificationArgumentResolver());

            PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
            argumentResolver.add(resolver);
            super.addArgumentResolvers(argumentResolver);
        }

}
