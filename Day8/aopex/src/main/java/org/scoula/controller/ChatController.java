package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.scoula.domain.ChatMessage;
import org.scoula.domain.GreetingMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Log4j2
public class ChatController {

    // 클라이언트가 WebSocket을 통해 "/hello" 주소로 메시지를 보내면 이 메서드가 호출됨
    @MessageMapping("/hello") // STOMP 메시지를 처리하는 엔드포인트 지정 (요청 경로)
    @SendTo("/topic/greetings") // 처리된 결과 메시지를 "/topic/greetings" 구독자들에게 브로드캐스팅
    public GreetingMessage greeting(GreetingMessage message) throws Exception {
        log.info("greeting: " + message); // 받은 메시지를 로그로 출력
        return message; // 받은 메시지를 그대로 반환하여 클라이언트들에게 전달
    }

    // 클라이언트가 WebSocket을 통해 "/chat" 주소로 메시지를 보내면 이 메서드가 호출됨
    @MessageMapping("/chat") // STOMP 메시지를 처리하는 엔드포인트 지정 (요청 경로)
    @SendTo("/topic/chat") // 처리된 결과 메시지를 "/topic/chat"을 구독 중인 클라이언트들에게 전송
    public ChatMessage chat(ChatMessage message) throws Exception {
        log.info("chat received: " + message); // 받은 채팅 메시지를 로그로 출력
        return message; // 메시지를 그대로 반환하여 실시간 채팅방에 전달
    }
}
