package edu.asoldatov.bet.uefa.service;

import edu.asoldatov.bet.uefa.configuration.UefaProperties;
import edu.asoldatov.bet.uefa.dto.UefaMatch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class UefaClient {

    private final RestTemplate restTemplate;
    private final UefaProperties uefaProperties;

    public UefaMatch[] getMatches() {
        var uri = uefaProperties.getUrl() + "matches" + "?competitionId=3&fromDate=2023-06-01&limit=500&offset=0&seasonYear=2024&toDate=2024-06-30&utcOffset=3";//todo

        return restTemplate.getForObject(uri, UefaMatch[].class);
    }

    public UefaMatch[] getMatches(LocalDate localDateFrom, LocalDate localDateTo) {
        String from = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDateFrom);
        String to = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDateTo);
        var uri = uefaProperties.getUrl() + "matches" + "?competitionId=3&fromDate=" + from + "&limit=10&offset=0&seasonYear=2024&toDate=" + to + "&utcOffset=3";

        System.out.println(uri);
        return restTemplate.getForObject(uri, UefaMatch[].class);
    }
}
