package ru.spb.iam;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.sql.ResultSet;
import java.sql.Statement;

public class SubscribeCommand extends ServiceCommand {
    private Logger logger = LoggerFactory.getLogger(HelpCommand.class);
    private javax.sql.DataSource myDS;

    public SubscribeCommand(String identifier, String description, javax.sql.DataSource myDS) {
        super(identifier, description);
        this.myDS = myDS;
    }

    @SneakyThrows
    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);

        logger.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName, this.getCommandIdentifier()));
        System.out.println(String.format("Пользователь %s. Начато выполнение команды %s", userName, this.getCommandIdentifier()));
        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                "\uD83D\uDC49 Тут мы подписываемся\n\n" +
                        "Желаю удачи\uD83D\uDE42");

        java.sql.Connection conn = myDS.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM subscribers";
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("The result is: ");
        while(rs.next()) System.out.println(rs.getString(3));
        conn.close();

        logger.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName, this.getCommandIdentifier()));
    }
}
