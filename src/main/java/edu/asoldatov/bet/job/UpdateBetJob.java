package edu.asoldatov.bet.job;

import edu.asoldatov.bet.bot.telegram.BetBot;
import edu.asoldatov.bet.bot.telegram.TelegramUtils;
import edu.asoldatov.bet.common.model.Bet;
import edu.asoldatov.bet.common.model.BetStatus;
import edu.asoldatov.bet.common.service.BetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateBetJob {

    private final BetService betService;
    private final BetBot telegramBot;

    @Scheduled(fixedRate = 1000)
    public void updateBets() {
        List<Bet> bets = betService.findAllReadyToUpdate();
        bets.forEach(this::update);
    }

    private void update(Bet bet) {
        try {
            var message = new EditMessageText(TelegramUtils.formatMessage(bet.getMatch(), bet.getHomeScore(), bet.getAwayScore()));
            message.setChatId(bet.getUser().getId());
            message.setMessageId(bet.getId());
            message.setReplyMarkup(TelegramUtils.getInlineKeyboardMarkup());
            telegramBot.execute(message);
        } catch (TelegramApiException e) {
            log.error("Can not update bet [{}] for user [{}]", bet.getId(), bet.getUser());
        }
        //todo сделать не ебано
        bet.setStatus(BetStatus.PROGRESS);
        betService.update(bet);
    }
}
