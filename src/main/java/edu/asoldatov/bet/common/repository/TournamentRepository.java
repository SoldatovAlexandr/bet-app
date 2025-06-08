package edu.asoldatov.bet.common.repository;

import edu.asoldatov.bet.common.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TournamentRepository extends JpaRepository<Tournament, Integer> {

    List<Tournament> findAllByDateBetween(LocalDateTime start, LocalDateTime finish);

}
