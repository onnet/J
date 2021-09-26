package ru.spb.iam.telebot.service_commands;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.spb.iam.telebot.Utils;

import static ru.spb.iam.db.Utils.maybeUserSubscribed;
import static ru.spb.iam.db.Utils.updateSubscription;

public class UnSubscribeCommand extends ServiceCommand {
    private Logger logger = LoggerFactory.getLogger(HelpCommand.class);

    public UnSubscribeCommand(String identifier, String description) {
        super(identifier, description);
    }

    @SneakyThrows
    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);
        String txtReply;

        logger.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName, this.getCommandIdentifier()));
        if (maybeUserSubscribed(user.getId())) {
            logger.debug("Надо отписаться");
            updateSubscription(user.getId(), false);
            txtReply = "\uD83D\uDC49 Вы успешно удалили подписку";
        } else {
            logger.debug("Уже отписан");
            txtReply = "Не суетитесь, Вы уже не подписаны. \uD83D\uDE42";
        }
        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName, txtReply);

        logger.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName, this.getCommandIdentifier()));
    }
}
