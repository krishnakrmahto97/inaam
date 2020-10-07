package io.inaam.main.config;


import io.inaam.main.handler.JWTFilter;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AllArgsConstructor
public class JwtConfig {
    private final JWTFilter jwtFilter;

    @Bean
    public FilterRegistrationBean<JWTFilter> filterRegistrationBean() {
        FilterRegistrationBean<JWTFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(jwtFilter);
        filterRegistrationBean.addUrlPatterns("/realm/*");
        return filterRegistrationBean;
    }
}
