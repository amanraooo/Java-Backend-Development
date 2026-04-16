package com.cfs.ChatBot.config;

import com.cfs.ChatBot.controller.GeminiWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	private GeminiWebSocketHandler geminiWebSocketHandler;

	@Autowired
	public WebSocketConfig(GeminiWebSocketHandler geminiWebSocketHandler){
		this.geminiWebSocketHandler= geminiWebSocketHandler;
	}



	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
		registry.addHandler(geminiWebSocketHandler, "/chat").setAllowedOrigins("*");
	}

}
