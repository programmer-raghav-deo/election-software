/**
 * 
 */
package com.deo.raghav;

import java.io.IOException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
/**
 * 
 */
public class Socket_Connection_Handler extends TextWebSocketHandler{
	
	static WebSocketSession session;
	
	@Override
 	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
 		//the messages will be broadcasted to all users.
		session.sendMessage(new TextMessage("Raghav Deo : Welcome to this socket connection !"));
 	}
	
	@Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
		if (message.getPayload().equals("Auth : Secret#1789")) {
			Socket_Connection_Handler.session = session;
		} else {
			
		}
	}
	
	public static void send_message(String message) {
				try {
					session.sendMessage(new TextMessage("Server : " + message));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
}
