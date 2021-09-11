package com.smart.springbootmsaccess.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class GlobalConfigurations {
    @Value("${jdbc.url}")
    private String jdbcUrl;

}
