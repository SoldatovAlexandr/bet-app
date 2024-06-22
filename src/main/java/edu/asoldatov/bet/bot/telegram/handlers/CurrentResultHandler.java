package edu.asoldatov.bet.bot.telegram.handlers;


import edu.asoldatov.bet.bot.telegram.TelegramContext;
import edu.asoldatov.bet.common.service.BetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurrentResultHandler extends CommandHandler {

    private final BetService betService;

    @Override
    protected boolean isMatch(String message) {
        return message.startsWith("/result");
    }

    @Override
    protected void handleCommand(TelegramContext context) {
        var result = betService.getCurrentResult();

        result.sort((v1, v2) -> {
            int r = v2.getScore() - v1.getScore();
            return r != 0 ? r : v1.getMatches() - v2.getMatches();
        });

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < result.size(); i++) {
            builder.append(getEmojiByPosition(i))
                    .append(result.get(i).getUser().getUsername())
                    .append(" ")
                    .append(result.get(i).getScore())
                    .append("\n");
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(context.getUser().getId());
        sendMessage.setText(builder.toString());

        try {
            context.getSelf().execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Can not send", e);
        }
    }

    private String getEmojiByPosition(int position) {
        Map<Integer, String> emojis = Map.of(
                0, "\uD83E\uDD47",
                1, "\uD83E\uDD48",
                2, "\uD83E\uDD49"
        );
        return emojis.getOrDefault(position, "\t\t");
    }
}
