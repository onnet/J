package ru.spb.iam.telebot.service_commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.spb.iam.telebot.Utils;

public class HelpCommand extends ServiceCommand {
    private Logger logger = LoggerFactory.getLogger(HelpCommand.class);

    public HelpCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);

        logger.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName, this.getCommandIdentifier()));
        System.out.println(String.format("Пользователь %s. Начато выполнение команды %s", userName, this.getCommandIdentifier()));
        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                "❗ *Список команд* " +
                        " \uD83D\uDC49 \n\n" +
                        "/subscribe - подписаться на рассылку\n" +
                        "/unsubscribe - отписаться от рассылки\n" +
                        "/help - эта страница\n\n" +
                        "Желаю удачи\uD83D\uDE42"
        );
        logger.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName, this.getCommandIdentifier()));
    }
}
