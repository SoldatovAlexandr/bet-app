package edu.asoldatov.bet.fifa.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class TournamentDto {

    private int id;
    private String venueName;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private LocalDateTime date;

    private long homeSquadId;
    private long awaySquadId;
    private String homeSquadName;
    private String awaySquadName;
    //todo
    private Object winner; // Может быть null
    private Object homeScore; // Может быть null
    private Object awayScore; // Может быть null
    private Object homePenaltyScore; // Может быть null
    private Object awayPenaltyScore; // Может быть null
    private String status;
    private int bracketId;

}
