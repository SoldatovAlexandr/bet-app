package edu.asoldatov.bet.bot.telegram;

import edu.asoldatov.bet.bot.configuration.BotProperties;
import edu.asoldatov.bet.bot.telegram.handlers.Handler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BetBot extends TelegramLongPollingBot {

    private final BotProperties botProperties;

    private final List<Handler> handlers;

    public BetBot(BotProperties botProperties, List<Handler> handlers) {
        this.botProperties = botProperties;
        this.handlers = handlers.stream()
                .sorted(Comparator.comparing(Handler::priority))
                .collect(Collectors.toList());
    }

    @Override
    public void onUpdateReceived(Update update) {
        var context = TelegramContext.builder()
                .update(update)
                .self(this)
                .build();
        handlers.forEach(handler -> handler.handle(context));
    }

    @Override
    public String getBotUsername() {
        return botProperties.getName();
    }

    @Override
    public String getBotToken() {
        return botProperties.getToken();
    }
}
