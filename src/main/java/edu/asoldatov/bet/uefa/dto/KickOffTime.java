package edu.asoldatov.bet.uefa.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class KickOffTime {
    //  "kickOffTime": {
    //    "date": "2024-06-14",
    //    "dateTime": "2024-06-14T19:00:00Z",
    //    "utcOffsetInHours": 2
    //  },

    private LocalDate date;
    private LocalDateTime dateTime; //todo
    private int utcOffsetInHours;
}
