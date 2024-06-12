package edu.asoldatov.bet.uefa.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UefaConfiguration {

    @Bean
    public RestTemplate uefaRestTemplate(){
        return new RestTemplate();
    }
}
