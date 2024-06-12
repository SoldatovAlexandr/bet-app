package edu.asoldatov.bet.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bet")
public class Bet {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "home_score")
    private int homeScore;

    @Column(name = "away_score")
    private int awayScore;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "score")
    private int score;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BetStatus status;

}
