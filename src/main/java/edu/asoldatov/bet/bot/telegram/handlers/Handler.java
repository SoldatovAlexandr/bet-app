package edu.asoldatov.bet.bot.telegram.handlers;

import edu.asoldatov.bet.bot.telegram.TelegramContext;

public interface Handler {

    void handle(TelegramContext context);

    Priority priority();
}
