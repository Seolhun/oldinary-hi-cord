package com.hi.cord.config.web;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
    	config.enableSimpleBroker("/queue");
        config.setApplicationDestinationPrefixes("/hi-cord");
        config.setUserDestinationPrefix("/user-message");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/message").withSockJS();
    }

	@Override
	public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
		messageConverters.add(converter());
		return super.configureMessageConverters(messageConverters);
	}
	
	@Bean
	public MessageConverter converter(){
		return new MappingJackson2MessageConverter();
	}
}