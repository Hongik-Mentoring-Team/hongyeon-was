package com.hongik.mentor.hongik_mentor.config;

import com.hongik.mentor.hongik_mentor.constant.ConstantUri;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    // 클라이언트가 메시지를 주고받을 엔드포인트(Handshake 용)
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 예: "/ws-stomp" 라는 엔드포인트를 열고, 소켓연결 ,SockJS 사용 가능하도록
        registry.addEndpoint("/ws-stomp")
                .setAllowedOrigins(ConstantUri.FRONT_URL) //프론트서버 출처의 요청은 ok
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 서버가 구독(subscribe)할 endpoint 경로 prefix
        // 예: /topic, /queue 등의 브로커를 사용
        // 인메모리 브로커 -> 추후 redis또는 RabbitMQ와 같은 외부 메시지 브로커도 사용가능
        registry.enableSimpleBroker("/topic", "/queue");

        // 클라이언트가 메시지를 보낼 때 붙이는 prefix
        // 예: /app/... -> @MessageMapping으로 라우팅
        registry.setApplicationDestinationPrefixes("/app");    }
}
