package org.example.filter.interceptor.config;

import org.example.filter.interceptor.OpenApiInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new OpenApiInterceptor())
                .addPathPatterns("/**");
    }
}
