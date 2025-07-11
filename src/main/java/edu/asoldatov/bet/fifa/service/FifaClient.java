package edu.asoldatov.bet.fifa.service;

import edu.asoldatov.bet.fifa.configuration.FifaProperties;
import edu.asoldatov.bet.fifa.dto.StageDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Component
public class FifaClient {

    private final RestTemplate restTemplate;
    private final FifaProperties fifaProperties;

    public FifaClient(@Qualifier("fifa") RestTemplate restTemplate, FifaProperties fifaProperties) {
        this.restTemplate = restTemplate;
        this.fifaProperties = fifaProperties;
    }

    public StageDto[] getMatches() {
        var uri = fifaProperties.getUrl();

        return restTemplate.getForObject(uri, StageDto[].class);
    }
}
