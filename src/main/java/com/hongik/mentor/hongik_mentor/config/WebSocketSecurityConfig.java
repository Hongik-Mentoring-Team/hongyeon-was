package com.hongik.mentor.hongik_mentor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;
/* => StompChannelInterceptor에 세션검증 위임함

@Configuration
@EnableWebSocketSecurity
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
                // /app/** 전송은 인증된 사용자만
                .simpDestMatchers("/app/**").authenticated()
                // /topic/chat/** 구독은 인증된 사용자만
                .simpSubscribeDestMatchers("/topic/chat/**").authenticated()
                .anyMessage().denyAll();
    }

    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}
*/
