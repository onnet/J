package ru.spb.iam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class UnSubscribeCommand extends ServiceCommand {
    private Logger logger = LoggerFactory.getLogger(HelpCommand.class);
    ConnectionPool connectionPool;

    public UnSubscribeCommand(String identifier, String description, ConnectionPool connectionPool) {
        super(identifier, description);
        this.connectionPool = connectionPool;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);

        logger.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName, this.getCommandIdentifier()));
        System.out.println(String.format("Пользователь %s. Начато выполнение команды %s", userName, this.getCommandIdentifier()));
        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                "\uD83D\uDC49 Тут мы отписываемся\n\n" +
                        "Желаю удачи\uD83D\uDE42");
        logger.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName, this.getCommandIdentifier()));
    }
}
