package edu.asoldatov.bet.bot.telegram.handlers;

import edu.asoldatov.bet.bot.telegram.TelegramContext;
import edu.asoldatov.bet.common.model.UserStatus;
import edu.asoldatov.bet.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StopHandler extends CommandHandler {

    private final UserRepository userRepository;

    @Override
    protected boolean isMatch(String message) {
        return message.startsWith("/stop");
    }

    @Override
    protected void handleCommand(TelegramContext context) {
        var user = context.getUser();
        user.setStatus(UserStatus.STOPPED);
        userRepository.save(user);
    }
}

