package edu.asoldatov.bet.bot.telegram.handlers;


import edu.asoldatov.bet.bot.telegram.TelegramContext;
import edu.asoldatov.bet.common.model.User;
import edu.asoldatov.bet.common.service.BetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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

        StringBuilder builder = new StringBuilder();

        for (User user : result.keySet()) {
            builder.append(user.getUsername()).append(" ").append(result.get(user)).append("\n");
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
}
