package com.hongik.mentor.hongik_mentor.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
public class StompChannelInterceptor implements ChannelInterceptor {
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public StompChannelInterceptor(@Lazy SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
            String destination = accessor.getDestination();
            String sessionId = accessor.getSessionId();
            String user = accessor.getUser() != null ? accessor.getUser().getName() : "Unknown";

            // 구독 요청 검증 로직 추가
            if (isSubscriptionAllowed(destination, user)) {
                log.info("User {} with SessionId {} is allowed to subscribe to {}", user, sessionId, destination);
                // 구독 허용
            } else {
                log.info("User {} with SessionId {} is NOT allowed to subscribe to {}", user, sessionId, destination);
                // 구독 거부
                throw new IllegalArgumentException("Subscription to destination [" + destination + "] is not allowed.");
            }

        } else if (StompCommand.SEND.equals(accessor.getCommand())) {
            String destination = accessor.getDestination();
            String sessionId = accessor.getSessionId();
            String user = accessor.getUser() != null ? accessor.getUser().getName() : "Unknown";

            // 클라이언트의 메시지 전송 요청만 검증
            if (isClientSend(destination)) {
                if (isMessageSendAllowed(destination, user)) {
                    System.out.println("User [" + user + "] with Session ID [" + sessionId + "] is allowed to send message to [" + destination + "]");
                    // 메시지 전송 허용
                } else {
                    System.out.println("User [" + user + "] with Session ID [" + sessionId + "] is NOT allowed to send message to [" + destination + "]");
                    // 메시지 전송 거부
                    messagingTemplate.convertAndSendToUser(user, "/queue/errors",
                            Collections.singletonMap("message", "sending messages to destination " + destination + "is NOT allowed"));  //해당 에러url을 사용자가 구독해놨어야지 전송된다
                    throw new IllegalArgumentException("Sending messages to destination [" + destination + "] is not allowed.");
                }
            }
            // 서버 측의 메시지 전송은 인터셉터에서 무시 (검증하지 않음)
        }

        return message;
    }

    /**
     * 구독 요청이 허용되는지 여부를 검증하는 메서드
     *
     * @param destination 구독 목적지
     * @param user        사용자 이름
     * @return 허용 여부
     */
    private boolean isSubscriptionAllowed(String destination, String user) {
        // 예시 조건:
        // - 특정 채팅방만 구독 가능
        // - 사용자 권한에 따라 구독 허용 여부 결정

        // 여기서는 예시로 "/topic/chat/{roomId}" 형식의 목적지만 허용
        if (destination != null && destination.startsWith("/topic/chat/")) {
            // 추가적인 검증 로직을 여기에 추가할 수 있습니다.
            return true;
        }

        return false;
    }

    /**
     * 메시지 전송 요청이 클라이언트로부터 온 것인지 확인하는 메서드
     *
     * @param destination 메시지를 전송할 목적지
     * @return 클라이언트의 전송 요청 여부
     */
    private boolean isClientSend(String destination) {
        // 클라이언트는 "/app/*" 경로로 메시지를 전송
        return destination != null && destination.startsWith("/app/");
    }

    /**
     * 메시지 전송 요청이 허용되는지 여부를 검증하는 메서드
     *
     * @param destination 메시지를 전송할 목적지
     * @param user        사용자 이름
     * @return 허용 여부
     */
    private boolean isMessageSendAllowed(String destination, String user) {
        // 예시 조건:
        // - "/app/chat/message" 경로로의 메시지 전송만 허용
        // - 사용자 권한에 따라 메시지 전송 허용 여부 결정

        // 여기서는 예시로 "/app/chat/message" 경로로의 메시지 전송만 허용
        if (destination != null && destination.equals("/app/chat/message")) {
            // 추가적인 검증 로직을 여기에 추가할 수 있습니다.
            return true;
        }

        return false;
    }
}
