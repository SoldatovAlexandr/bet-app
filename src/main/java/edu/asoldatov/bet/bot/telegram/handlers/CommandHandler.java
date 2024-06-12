package edu.asoldatov.bet.bot.telegram.handlers;

import edu.asoldatov.bet.bot.telegram.TelegramContext;

public abstract class CommandHandler implements Handler {

    @Override
    public final void handle(TelegramContext context) {
        var update = context.getUpdate();
        if (update.hasMessage() && update.getMessage().hasText() && isMatch(update.getMessage().getText())) {
            handleCommand(context);
        }
    }

    protected abstract boolean isMatch(String message);

    protected abstract void handleCommand(TelegramContext context);

    @Override
    public final Priority priority() {
        return Priority.ACTION;
    }
}
