package edu.asoldatov.bet.job;

import edu.asoldatov.bet.bot.telegram.BetBot;
import edu.asoldatov.bet.common.model.User;
import edu.asoldatov.bet.common.model.UserStatus;
import edu.asoldatov.bet.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateUserJob {

    private final BetBot telegramBot;
    private final UserRepository repository;

    @Scheduled(fixedRate = 1000)
    public void updateUser() {
        List<User> users = repository.findAllByStatus(UserStatus.PREPARED);
        users.forEach(this::update);
    }

    private void update(User user) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(user.getId());
        sendMessage.setText("""
                Поздравляю!
                Теперь ты участвуешь в наших ставках на ЧЕ24.
                Каждый игровой день я буду присылать тебе список матчей.
                Твоя задача выбрать счет матча.
                - За каждый угаданный результат ты получишь 1 очко.
                - За угаданный точный счет получишь 3 очка.
                В конце каждого дня мы подводим итоги.
                (Чтобы остановть участие достаточно написать мне /stop )
                Удачи (если ты не Никитос)!
                """
        );
        user.setStatus(UserStatus.ACTIVE);
        repository.save(user);
        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Can not send message for user [{}]", user);
        }
    }
}
