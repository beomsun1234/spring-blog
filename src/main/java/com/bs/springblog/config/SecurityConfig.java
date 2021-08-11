package com.bs.springblog.config;

import com.bs.springblog.config.auth.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()

                .and()
                .authorizeRequests()
                .antMatchers("/img/**", "/", "/js/**", "/starter-template.css", "/fragment/**", "/h2/**", "/h2-console/**", "/board/list/**").permitAll()
                .antMatchers("/api/v1/**").hasRole("USER")
                .and()
                    .logout().logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                .and()
                    .oauth2Login()
                    .loginPage("/loginForm")
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService);


    }
}
