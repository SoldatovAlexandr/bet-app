package edu.asoldatov.bet.fifa.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.fifa.integration")
public class FifaProperties {

    private String url;

}
