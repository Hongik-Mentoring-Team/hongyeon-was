package com.hongik.mentor.hongik_mentor.config;

import com.hongik.mentor.hongik_mentor.domain.Role;
import com.hongik.mentor.hongik_mentor.oauth.CustomAuthenticationSuccessHandler;
import com.hongik.mentor.hongik_mentor.oauth.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/member", "/h2-console/**", "/profile").permitAll()  //해당 url은 인증없이도 접근 가능
                        .requestMatchers("/api/**").hasRole(Role.USER.name())
                        .anyRequest().authenticated()   //그외 url은 인증요구
                )
                .logout(logout -> logout    //로그아웃 처리
                        .logoutSuccessUrl("/")
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true)
                )

                .oauth2Login(oauth2 -> oauth2   //소셜로그인 성공 시 처리
                        .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
                        .successHandler(authenticationSuccessHandler));
        return httpSecurity.build();

    }
}
