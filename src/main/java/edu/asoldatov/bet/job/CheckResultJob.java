package edu.asoldatov.bet.job;

import edu.asoldatov.bet.common.model.Bet;
import edu.asoldatov.bet.common.model.Tournament;
import edu.asoldatov.bet.common.repository.BetRepository;
import edu.asoldatov.bet.common.repository.TournamentRepository;
import edu.asoldatov.bet.common.service.DateService;
import edu.asoldatov.bet.fifa.dto.StageDto;
import edu.asoldatov.bet.fifa.dto.TournamentDto;
import edu.asoldatov.bet.fifa.service.FifaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CheckResultJob {

    private final DateService dateService;
    private final FifaClient fifaClient;
    private final TournamentRepository tournamentRepository;
    private final BetRepository betRepository;

    @Transactional
    @Scheduled(fixedRate = 60_000)
    public void check() {
        var result = fifaClient.getMatches();

        for (StageDto stageDto : result) {
            if (!"scheduled".equals(stageDto.getStatus())) {


                for (TournamentDto tournamentDto : stageDto.getTournaments()) {
                    if (!"scheduled".equals(tournamentDto.getStatus())) {

                        Tournament tournament = tournamentRepository.getById(tournamentDto.getId());
                        updateTournament(tournamentDto, tournament);
                        var bets = betRepository.findByTournament(tournament);

                        for (Bet bet : bets) {
                            updateBet(bet, tournament);
                        }
                    }
                }
            }
        }
    }

    private void updateTournament(TournamentDto tournamentDto, Tournament tournament) {

        tournament.setHomePenaltyScore(tournamentDto.getHomePenaltyScore());
        tournament.setAwayPenaltyScore(tournamentDto.getAwayPenaltyScore());
        tournament.setAwayScore(tournamentDto.getAwayScore());
        tournament.setHomeScore(tournamentDto.getHomeScore());
        tournament.setWinner(tournamentDto.getWinner());

        tournamentRepository.save(tournament);
    }

    private void updateBet(Bet bet, Tournament tournament) {
        int betResult = bet.getAwayScore() - bet.getHomeScore();
        int tournamentResult = tournament.getAwayScore() == null ? 0 : tournament.getAwayScore() - tournament.getHomeScore();
        boolean result = betResult * tournamentResult > 0;
        int score = tournament.getAwayScore() == null ? 0 : (bet.getAwayScore() == tournament.getAwayScore() && bet.getHomeScore() == tournament.getHomeScore()) ? 3 : (result) ? 1 : 0;
        bet.setScore(score);
        betRepository.save(bet);
    }

}
