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

import static ru.spb.iam.db.Utils.maybeLLExists;
import static ru.spb.iam.db.Utils.maybeUserExists;

public class SubscribeCommand extends ServiceCommand {
    private Logger logger = LoggerFactory.getLogger(HelpCommand.class);

    public SubscribeCommand(String identifier, String description) {
        super(identifier, description);
    }

    @SneakyThrows
    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);

        logger.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName, this.getCommandIdentifier()));
        logger.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName, this.getCommandIdentifier()));
        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                "\uD83D\uDC49 Тут мы подписываемся\n\n" +
                        "Желаю удачи\uD83D\uDE42");
        DbConnection dbCon = DbConnection.getInstance();
        ConnectionPool connectionPool = dbCon.getPool();
        Connection conn = connectionPool.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM subscribers";
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("The result is: ");
        while(rs.next()) System.out.println(rs.getString(3));
        conn.close();

        if (maybeUserExists(user.getId())) {
            logger.debug("Пользоваьель существует");
        } else {
            logger.debug("Пользоваьель НЕ существует)");
        }

        if (maybeLLExists(user.getId())) {
            logger.debug("Координаты введены");
        } else {
            logger.debug("Координаты НЕ введены");
        }


        logger.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName, this.getCommandIdentifier()));
    }
}
