package edu.asoldatov.bet.common.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tournament")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tournament {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "venue_name")
    private String venueName;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "home_squad_id", nullable = false)
    private Long homeSquadId;

    @Column(name = "away_squad_id", nullable = false)
    private Long awaySquadId;

    @Column(name = "home_squad_name", nullable = false)
    private String homeSquadName;

    @Column(name = "away_squad_name", nullable = false)
    private String awaySquadName;

    @Column(name = "status")
    private String status;

    @Column(name = "bracket_id")
    private Integer bracketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id", nullable = false)
    private Stage stage;
}
