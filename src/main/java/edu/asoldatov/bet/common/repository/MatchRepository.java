package edu.asoldatov.bet.common.repository;

import edu.asoldatov.bet.common.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, String> {

    List<Match> findAllByTimeBetween(LocalDateTime start, LocalDateTime finish);

}
