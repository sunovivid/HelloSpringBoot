package org.sunovivid.hellospringbootagain.springboot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.sunovivid.hellospringbootagain.springboot.config.auth.LoginUserArgumentResolver;

import java.util.List;

//WebMvcConfigurer
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    //HandlerMethodArgumentResolver에 우리가 만든 argumentResolver 추가
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserArgumentResolver);
    }
}
