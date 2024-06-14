package edu.asoldatov.bet.common.service;

import edu.asoldatov.bet.common.model.*;
import edu.asoldatov.bet.common.repository.BetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static edu.asoldatov.bet.common.model.ScoreChange.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BetService {

    private final BetRepository betRepository;
    private final DateService dateService;

    public void create(Integer id, User user, Match match) {
        var bet = Bet.builder()
                .id(id)
                .match(match)
                .user(user)
                .awayScore(0)
                .homeScore(0)
                .score(0)
                .createdAt(dateService.now())
                .status(BetStatus.PROGRESS)
                .build();
        betRepository.save(bet);
    }

    public Optional<Bet> findByUserAndMatch(Match match, User user) {
        return betRepository.findByUserAndMatch(user, match);
    }

    public Optional<Bet> findById(Integer id) {
        return betRepository.findById(id);
    }

    public void changeScore(Bet bet, String callbackData) {
        if (isMatchNotStarted(bet) && scoreIsCorrect(bet, callbackData)) {
            switch (getByLabel(callbackData)) {
                case UP_HOME -> bet.setHomeScore(bet.getHomeScore() + 1);
                case UP_AWAY -> bet.setAwayScore(bet.getAwayScore() + 1);
                case DOWN_HOME -> bet.setHomeScore(bet.getHomeScore() - 1);
                case DOWN_AWAY -> bet.setAwayScore(bet.getAwayScore() - 1);
            }
            bet.setStatus(BetStatus.READY_TO_UPDATE);
            betRepository.save(bet);
        }
        log.error("Can not change bet");
    }

    public void update(Bet bet) {
        betRepository.save(bet);
    }

    /*
    запущены на одной ноде, поэтому можно без блокировки
    + операция идемпотентна
     */
    public List<Bet> findAllReadyToUpdate() {
        return betRepository.findAllByStatus(BetStatus.READY_TO_UPDATE);
    }

    public List<ReportItem> getCurrentResult() {
        var bets = betRepository.findAll();
        Map<User, Set<Bet>> result = new HashMap<>();

        for (Bet bet : bets) {
            User user = bet.getUser();
            var set = result.getOrDefault(user, new HashSet<>());
            set.add(bet);
            result.put(user, set);
        }

        List<ReportItem> items = new ArrayList<>(result.size());

        for (User user : result.keySet()) {
            var userBets = result.get(user);
            int score = userBets.stream().mapToInt(Bet::getScore).sum();
            int matchCount = userBets.size();

            items.add(new ReportItem().setUser(user).setScore(score).setMatches(matchCount));
        }

        return items;
    }

    private boolean isMatchNotStarted(Bet bet) {
        return bet.getMatch().getTime().isAfter(dateService.now().minusHours(3));
    }

    private boolean scoreIsCorrect(Bet bet, String callbackData) {
        if (bet.getHomeScore() == 0 && callbackData.equals(DOWN_HOME.getLabel())) {
            return false;
        }
        return bet.getAwayScore() != 0 || !callbackData.equals(DOWN_AWAY.getLabel());
    }
}
