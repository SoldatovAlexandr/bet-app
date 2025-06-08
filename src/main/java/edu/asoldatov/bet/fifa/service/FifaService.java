package edu.asoldatov.bet.fifa.service;

import edu.asoldatov.bet.common.model.Stage;
import edu.asoldatov.bet.common.model.Tournament;
import edu.asoldatov.bet.common.repository.StageRepository;
import edu.asoldatov.bet.fifa.dto.StageDto;
import edu.asoldatov.bet.fifa.dto.TournamentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FifaService {

    private final FifaClient fifaClient;
    private final StageRepository stageRepository;

    @PostConstruct
    public void init() {
        StageDto[] result = fifaClient.getMatches();

        var stages = map(result);


        stageRepository.saveAll(stages);


    }

    private List<Stage> map(StageDto[] stages) {
        var result = Arrays.stream(stages).map(this::map).toList();
        for (var stage : result) {
            stage.getTournaments().forEach(tournament -> tournament.setStage(stage));
        }
        return result;
    }

    private Stage map(StageDto stageDto) {
        return Stage.builder()
                .id(stageDto.getId())
                .stage(stageDto.getStage())
                .status(stageDto.getStatus())
                .startDate(stageDto.getStartDate())
                .endDate(stageDto.getEndDate())
                .tournaments(map(stageDto.getTournaments()))
                .build();
    }

    private List<Tournament> map(List<TournamentDto> tournaments) {
        return tournaments.stream().map(this::map).toList();
    }

    private Tournament map(TournamentDto tournamentDto) {
        return Tournament.builder()
                .id(tournamentDto.getId())
                .venueName(tournamentDto.getVenueName())
                .date(tournamentDto.getDate())
                .homeSquadId(tournamentDto.getHomeSquadId())
                .awaySquadId(tournamentDto.getAwaySquadId())
                .homeSquadName(tournamentDto.getHomeSquadName())
                .awaySquadName(tournamentDto.getAwaySquadName())
                .status(tournamentDto.getStatus())
                .bracketId(tournamentDto.getBracketId())
                .build();
    }

}
