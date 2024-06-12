package edu.asoldatov.bet.bot.telegram.handlers;

import edu.asoldatov.bet.bot.telegram.TelegramContext;
import edu.asoldatov.bet.common.service.BetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CallbackHandler implements Handler {

    private final BetService betService;

    @Override
    public void handle(TelegramContext context) {
        var update = context.getUpdate();

        if (update.hasCallbackQuery()) {
            var callbackData = update.getCallbackQuery().getData();
            var message = update.getCallbackQuery().getMessage();

            betService.findById(message.getMessageId())
                    .ifPresentOrElse(
                            bet -> betService.changeScore(bet, callbackData),
                            () -> log.error("Bet not found")
                    );
        }
    }

    @Override
    public Priority priority() {
        return Priority.ACTION;
    }
}
