package edu.asoldatov.bet.fifa.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FifaConfiguration {

    @Bean
    @Qualifier("fifa")
    public RestTemplate fifaRestTemplate() {
        return new RestTemplate();
    }
}
