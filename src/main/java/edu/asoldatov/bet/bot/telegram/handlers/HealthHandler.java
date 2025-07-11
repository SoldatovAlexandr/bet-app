package edu.asoldatov.bet.bot.telegram.handlers;

import edu.asoldatov.bet.bot.telegram.TelegramContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class HealthHandler extends CommandHandler {

    @Override
    protected boolean isMatch(String message) {
        return message.startsWith("/health");
    }

    @Override
    protected void handleCommand(TelegramContext context) {
        var bot = context.getSelf();
        var user = context.getUser();
        try {
            bot.execute(new SendMessage(String.valueOf(user.getId()), "OK"));
        } catch (TelegramApiException e) {
            log.error("Can not response on health check", e);
        }
    }
}
