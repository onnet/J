package ru.spb.iam.telebot.service_commands;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.spb.iam.db.ConnectionPool;
import ru.spb.iam.db.DbConnection;
import ru.spb.iam.telebot.Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

import static ru.spb.iam.db.Utils.*;
import static ru.spb.iam.telebot.Utils.getUserName;

public class SubscribeCommand extends ServiceCommand {
    private Logger logger = LoggerFactory.getLogger(SubscribeCommand.class);

    public SubscribeCommand(String identifier, String description) {
        super(identifier, description);
    }

    @SneakyThrows
    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = getUserName(user);
        String txtReply;
        logger.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName, this.getCommandIdentifier()));

        if (maybeUserExists(user.getId())) {
            logger.debug("Пользователь существует");
        } else {
            logger.debug("Пользователь НЕ существует)");
            addUser(user.getId(), getUserName(user), user.getFirstName(), user.getLastName());
        }
        logger.debug("После updateSubscription");
        if (maybeUserSubscribed(user.getId())) {
            logger.debug("Уже подписан");
            if (maybeLLExists(user.getId())) {
                txtReply = "Вы уже подписаны. \uD83D\uDE42";
            } else {
                txtReply = "Вы уже подписаны, но не забудте прислать Ваше местоположение. \uD83D\uDE42";
            }
        } else {
            updateSubscription(user.getId(), true);
            if (maybeLLExists(user.getId())) {
                logger.debug("Координаты присутствуют");
                txtReply = "\uD83D\uDC49 Вы успешно подписались на рассылку погоды";
            } else {
                logger.debug("Координаты отсутствуют");
                txtReply = "\uD83D\uDC49 Для завершения подписки Вам необходимо отправить Ваши текущие координаты";
            }
        }
        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName, txtReply);

        logger.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName, this.getCommandIdentifier()));
    }
}
