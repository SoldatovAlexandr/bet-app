package edu.asoldatov.bet.bot.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.telegram.bot.bet")
public class BotProperties {

    private String name;
    private String token;

}
