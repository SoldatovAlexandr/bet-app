package edu.asoldatov.bet.bot.telegram;

import edu.asoldatov.bet.common.model.Tournament;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

import static edu.asoldatov.bet.common.model.ScoreChange.*;
import static edu.asoldatov.bet.common.model.ScoreChange.DOWN_AWAY;

@UtilityClass
public class TelegramUtils {

    public InlineKeyboardMarkup getInlineKeyboardMarkup() {
        return new InlineKeyboardMarkup(List.of(
                List.of(
                        buildButton("+", UP_HOME.getLabel()),
                        buildButton("+", UP_AWAY.getLabel())
                ),
                List.of(
                        buildButton("-", DOWN_HOME.getLabel()),
                        buildButton("-", DOWN_AWAY.getLabel())
                )
        ));
    }

    public InlineKeyboardButton buildButton(String text, String callbackData) {
        var button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }


    public String formatMessage(Tournament tournament, int homeScore, int awayScore) {
        return String.format("""
                        %s : %s
                        %s : %s
                        %s
                        """,
                tournament.getHomeSquadName(), tournament.getAwaySquadName(),
                homeScore, awayScore,
                tournament.getDate() // todo format
                //todo можно намного больше инфы качать!!!
        );
    }
}
