package ru.spb.iam;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.sql.SQLException;
import java.util.Map;

public class Main {
    private static final Map<String, String> getenv = System.getenv();

    public static void main(String[] args) {

        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "Debug");

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            System.out.println("BOT_NAME: " + System.getenv("BOT_NAME"));
            System.out.println("BOT_TOKEN: " + System.getenv("BOT_TOKEN"));
            botsApi.registerBot(new Bot(System.getenv("BOT_NAME"), System.getenv("BOT_TOKEN")));
        } catch (TelegramApiException | SQLException e) {
            e.printStackTrace();
        }
    }
}
