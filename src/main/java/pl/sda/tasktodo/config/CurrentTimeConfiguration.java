package pl.sda.tasktodo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class CurrentTimeConfiguration {

    @Bean
    LocalDateTime now(){
        return LocalDateTime.now();
    }
}
