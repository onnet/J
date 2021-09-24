package ru.spb.iam;

        import lombok.Getter;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
        import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
        import org.telegram.telegrambots.meta.api.objects.Message;
        import org.telegram.telegrambots.meta.api.objects.Update;
        import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

        import java.util.HashMap;
        import java.util.Map;

public final class Bot extends TelegramLongPollingCommandBot {
    private Logger logger = LoggerFactory.getLogger(Bot.class);
    javax.sql.DataSource myDS = createDataSource ();

    private final String BOT_NAME;
    private final String BOT_TOKEN;

    @Getter
    private final NonCommand nonCommand;

    public Bot(String botName, String botToken) {
        super();
        logger.debug("Конструктор суперкласса отработал");
        this.BOT_NAME = botName;
        this.BOT_TOKEN = botToken;
        logger.debug("Имя и токен присвоены");

        this.nonCommand = new NonCommand();
        logger.debug("Класс обработки сообщения, не являющегося командой, создан");

        register(new HelpCommand("help","Помощь"));
        logger.debug("Команда help создана");
        register(new SubscribeCommand("subscribe","Подписаться", myDS));
        logger.debug("Команда subscribe создана");
        register(new UnSubscribeCommand("unsubscribe","Отменить подписку", myDS));
        logger.debug("Команда unsubscribe создана");

        logger.info("Бот создан!");
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        Message msg = update.getMessage();
        logger.debug(String.valueOf(msg));
        Long chatId = msg.getChatId();
        String userName = Utils.getUserName(msg);

        String answer = nonCommand.nonCommandExecute(update);
        setAnswer(chatId, userName, answer);
    }

    private void setAnswer(Long chatId, String userName, String text) {
        SendMessage answer = new SendMessage();
        answer.setText(text);
        answer.setChatId(chatId.toString());
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            logger.error(String.format("Ошибка %s. Сообщение, не являющееся командой. Пользователь: %s", e.getMessage(),
                    userName));
            e.printStackTrace();
        }
    }

    private static javax.sql.DataSource createDataSource()
    {
        /* use a data source with connection pooling */
        org.postgresql.ds.PGPoolingDataSource ds = new org.postgresql.ds.PGPoolingDataSource();
        ds.setUrl(System.getenv("BOT_DB_URL"));
        ds.setUser(System.getenv("BOT_DB_USERNAME"));
        ds.setPassword(System.getenv("BOT_DB_PWD"));
        /* the connection pool will have 10 to 20 connections */
        ds.setInitialConnections(10);
        ds.setMaxConnections(20);
        /* use SSL connections without checking server certificate */
        ds.setSslMode("require");
        ds.setSslfactory("org.postgresql.ssl.NonValidatingFactory");
        return ds;
    }

}