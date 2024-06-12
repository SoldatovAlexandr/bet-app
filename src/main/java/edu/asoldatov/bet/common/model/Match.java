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
@Table(name = "match")
public class Match {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team home;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Team away;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "home_score")
    private int homeScore;

    @Column(name = "away_score")
    private int awayScore;

    @Column(name = "result")
    @Enumerated(EnumType.STRING)
    private Result result;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
