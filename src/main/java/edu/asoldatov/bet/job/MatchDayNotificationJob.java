package edu.asoldatov.bet.job;

import edu.asoldatov.bet.bot.telegram.BetBot;
import edu.asoldatov.bet.bot.telegram.TelegramUtils;
import edu.asoldatov.bet.common.model.Match;
import edu.asoldatov.bet.common.model.User;
import edu.asoldatov.bet.common.model.UserStatus;
import edu.asoldatov.bet.common.repository.MatchRepository;
import edu.asoldatov.bet.common.repository.UserRepository;
import edu.asoldatov.bet.common.service.BetService;
import edu.asoldatov.bet.common.service.DateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MatchDayNotificationJob {

    private final BetBot telegramBot;
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;
    private final DateService dateService;
    private final BetService betService;

    //todo

    //@Scheduled(fixedRate = 10000)
    @Scheduled(fixedRate = 3_600_000)
    public void sendNotifications() {
        log.info("Start sending notifications");
        LocalDate day = dateService.today();
        LocalDateTime start = LocalDateTime.of(day, LocalTime.MIN);
        LocalDateTime finish = LocalDateTime.of(day, LocalTime.MAX);

        var matches = matchRepository.findAllByTimeBetween(start, finish);

        var activeUsers = userRepository.findAllByStatus(UserStatus.ACTIVE);

        activeUsers.forEach(user -> sendNotifications(user, matches));
        log.info("Finish sending notifications");
    }

    private void sendNotifications(User user, List<Match> matches) {
        for (Match match : matches) {
            sendBetNotification(user, match);
        }
    }

    //todo блять переделать на аутбокс
    private void sendBetNotification(User user, Match match) {
        try {
            var bet = betService.findByUserAndMatch(match, user);
            if (bet.isEmpty()) {
                var message = buildBetMessage(user, match);
                var result = telegramBot.execute(message);
                betService.create(result.getMessageId(), user, match);
            }
        } catch (TelegramApiException e) {
            log.error("Can not send bet notification for user [{}]", user.getId(), e);
        }
    }

    private SendMessage buildBetMessage(User user, Match match) {
        var message = TelegramUtils.formatMessage(match, match.getHomeScore(), match.getAwayScore());
        var sendMessage = new SendMessage(String.valueOf(user.getId()), message);
        sendMessage.setReplyMarkup(TelegramUtils.getInlineKeyboardMarkup());
        return sendMessage;
    }
}
