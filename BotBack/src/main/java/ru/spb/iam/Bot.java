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
        register(new SubscribeCommand("subscribe","Помощь"));
        logger.debug("Команда subscribe создана");
        register(new UnSubscribeCommand("unsubscribe","Помощь"));
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
}