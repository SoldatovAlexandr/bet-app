package edu.asoldatov.bet.common.repository;

import edu.asoldatov.bet.common.model.Bet;
import edu.asoldatov.bet.common.model.BetStatus;
import edu.asoldatov.bet.common.model.Match;
import edu.asoldatov.bet.common.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BetRepository extends JpaRepository<Bet, Integer> {

    Optional<Bet> findByUserAndMatch(User user, Match match);

    List<Bet> findAllByStatus(BetStatus betStatus);

}
