package com.mclientui.configuration;

import com.mclientui.exceptions.CustomErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeingExceptionConfig {

    @Bean
    public CustomErrorDecoder customErrorDecoder(){
        return new CustomErrorDecoder();
    }
}
