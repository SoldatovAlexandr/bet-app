package edu.asoldatov.bet.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CheckResultJob {

//    private final DateService dateService;
//    private final UefaClient uefaClient;
//    private final MatchRepository matchRepository;
//    private final BetRepository betRepository;
//
//
//    //@Scheduled(fixedRate = 60_000)
//    public void check() {
//        var today = dateService.today();
//
//        var result = uefaClient.getMatches(today.minusDays(1), today);
//
//        for (UefaMatch uefaMatch : result) {
//            matchRepository.findById(uefaMatch.getId()).ifPresent(
//                    match -> {
//                        if (uefaMatch.getStatus().equals("FINISHED") && uefaMatch.getScore() != null && match.getResult() == Result.NONE) {
//                            updateMatch(uefaMatch, match);
//                            betRepository.findByMatch(match).forEach(bet -> updateBet(bet, match.getResult(), match.getHomeScore(), match.getAwayScore()));
//                        } else if (uefaMatch.getStatus().equals("LIVE")) {
//                            var score = uefaMatch.getScore().getTotal();
//                            var matchResult = calculateResult(score.getHome(), score.getAway());
//                            betRepository.findByMatch(match).forEach(bet -> updateBet(bet, matchResult, score.getHome(), score.getAway()));
//                        }
//                    }
//            );
//        }
//    }
//
//    private void updateMatch(UefaMatch uefaMatch, Match match) {
//        var score = uefaMatch.getScore().getTotal();
//        var matchResult = calculateResult(score.getHome(), score.getAway());
//        match.setResult(matchResult);
//        match.setHomeScore(score.getHome());
//        match.setAwayScore(score.getAway());
//        matchRepository.save(match);
//    }
//
//    private void updateBet(Bet bet, Result matchResult, int home, int away) {
//        var betResult = calculateResult(bet.getHomeScore(), bet.getAwayScore());
//        int score = (away == bet.getAwayScore() && home == bet.getHomeScore()) ? 3 : betResult == matchResult ? 1 : 0;
//        bet.setScore(score);
//        betRepository.save(bet);
//    }
//
//    private Result calculateResult(int home, int away) {
//        return Objects.equals(home, away) ? Result.DRAW : home > away ? Result.HOME : Result.AWAY;
//    }
}
