package edu.asoldatov.bet.bot.telegram.handlers;

import edu.asoldatov.bet.bot.telegram.TelegramContext;
import edu.asoldatov.bet.common.model.User;
import edu.asoldatov.bet.common.model.UserStatus;
import edu.asoldatov.bet.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class IdentificationHandler implements Handler {

    private final UserRepository userRepository;

    @Override
    public void handle(TelegramContext telegramContext) {
        var update = telegramContext.getUpdate();
        User user = extractUser(update);
        var userDb = userRepository.findById(user.getId());
        if (userDb.isEmpty()) {
            userRepository.save(user);
        }
        telegramContext.setUser(user);
    }

    @Override
    public Priority priority() {
        return Priority.INIT;
    }

    private User extractUser(Update update) {
        if (update.hasMessage()) {
            return extractUserFromMessage(update.getMessage());
        } else if (update.hasCallbackQuery()) {
            return extractUserFromMessage(update.getCallbackQuery().getMessage());
        }
        return User.builder().id(1L).build();//todo
    }

    private User extractUserFromMessage(Message message) {
        return User.builder()
                .id(message.getFrom().getId())
                .username(message.getFrom().getUserName())
                .firstName(message.getFrom().getFirstName())
                .status(UserStatus.NEW)
                .build();
    }
}
