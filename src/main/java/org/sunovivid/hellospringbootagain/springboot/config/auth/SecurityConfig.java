package org.sunovivid.hellospringbootagain.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    private final CustomOAuth2UserService customOAuth2UserService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().headers().frameOptions().disable()//h2 사용 위해 옵션들 disable
                .and()
                    .authorizeRequests() //url별 권한 관리 설정 옵션의 시작점. 이게 선언되어야만 antMatcher사용가능
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() //antMatcher: 권한 관리 대상 지정 옵션, 
                    .antMatchers("/api/v1/**").hasRole(USER.name()) //해당 주소 api는 USER 권한을 가진 사람만 가능
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                    .userService(customOAuth2UserService);
    }
    
}
