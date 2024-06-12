package edu.asoldatov.bet.bot.telegram;

import edu.asoldatov.bet.common.model.User;
import lombok.Builder;
import lombok.Data;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;


@Data
@Builder
public class TelegramContext {

    private Update update;

    private User user;

    private BetBot self;

}
