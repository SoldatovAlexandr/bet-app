package edu.asoldatov.bet.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum ScoreChange {

    UP_HOME("up_home"),
    UP_AWAY("up_away"),
    DOWN_HOME("down_home"),
    DOWN_AWAY("down_away");

    private final String label;

    public static ScoreChange getByLabel(String label) {
        return Arrays.stream(values())
                .filter(v -> v.label.equals(label))
                .findFirst()
                .orElseThrow();
    }

}
