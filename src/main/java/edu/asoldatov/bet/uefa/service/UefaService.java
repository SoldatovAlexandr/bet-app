package edu.asoldatov.bet.uefa.service;

import edu.asoldatov.bet.common.model.Match;
import edu.asoldatov.bet.common.model.Result;
import edu.asoldatov.bet.common.model.Team;
import edu.asoldatov.bet.common.repository.MatchRepository;
import edu.asoldatov.bet.common.repository.TeamRepository;
import edu.asoldatov.bet.uefa.dto.UefaMatch;
import edu.asoldatov.bet.uefa.dto.UefaTeam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UefaService {

    private final UefaClient uefaClient;
    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    //@PostConstruct
    public void initialize() {
        //todo
        log.info("Starting initialize");
        var uefaMatches = uefaClient.getMatches();

        Set<UefaTeam> uefaTeams = new HashSet<>();

        for (UefaMatch uefaMatch : uefaMatches) {
            uefaTeams.add(uefaMatch.getAwayTeam());
            uefaTeams.add(uefaMatch.getHomeTeam());
        }

        var teams = uefaTeams.stream()
                .map(uefaTeam -> Team.builder()
                        .id(uefaTeam.getId())
                        .name(uefaTeam.getInternationalName())
                        .build())
                .collect(Collectors.toMap(Team::getId, v -> v));

        var matches = Arrays.stream(uefaMatches).map(
                uefaMatch -> Match.builder()
                        .id(uefaMatch.getId())
                        .result(Result.NONE)
                        .time(uefaMatch.getKickOffTime().getDateTime())
                        .home(teams.get(uefaMatch.getHomeTeam().getId()))
                        .away(teams.get(uefaMatch.getAwayTeam().getId()))
                        .createdAt(LocalDateTime.now())
                        .build()
        ).toList();

        teamRepository.saveAll(teams.values());
        matchRepository.saveAll(matches);

        log.info("Finish initialize");
    }
}
