package com.hongik.mentor.hongik_mentor.config;

import com.hongik.mentor.hongik_mentor.constant.ConstantUri;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

public class CorsConfig {
    public static CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of(ConstantUri.NEED_TO_CHAGE));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(List.of(ConstantUri.NEED_TO_CHAGE));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource urlSource = new UrlBasedCorsConfigurationSource();  //UrlBased...는 CorsConfigSource 구현체
        // 모든 경로에 대해 위의 CORS 설정을 적용합니다.
        urlSource.registerCorsConfiguration("/**", config);

        return urlSource;
    }
}
