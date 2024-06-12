package edu.asoldatov.bet.uefa.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.uefa.integration")
public class UefaProperties {

    private String url;

}
