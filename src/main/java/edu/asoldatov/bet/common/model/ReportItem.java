package edu.asoldatov.bet.common.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ReportItem {

    private User user;

    private int score;

    private int matches;

}
