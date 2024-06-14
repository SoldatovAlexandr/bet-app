package edu.asoldatov.bet.uefa.dto;

import lombok.Data;

@Data
public class UefaMatch {

    private String id;

    private UefaTeam awayTeam;

    private UefaTeam homeTeam;

    private KickOffTime kickOffTime;

    private String status;

    private UefaScore score;
}
