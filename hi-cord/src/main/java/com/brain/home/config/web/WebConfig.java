package com.brain.home.config.web;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
//If you don't put WebMvcAutoConfiguration, Not Working Webjars 
public class WebConfig extends WebMvcAutoConfiguration{
	
}
