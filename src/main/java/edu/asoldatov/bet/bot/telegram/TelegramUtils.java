package edu.asoldatov.bet.bot.telegram;

import edu.asoldatov.bet.common.model.Match;
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


    public String formatMessage(Match match, int homeScore, int awayScore) {
        return String.format("""
                        %s : %s
                        %s : %s
                        %s
                        """,
                match.getHome().getName(), match.getAway().getName(),
                homeScore, awayScore,
                match.getTime() // todo format
                //todo можно намного больше инфы качать!!!
        );
    }
}
