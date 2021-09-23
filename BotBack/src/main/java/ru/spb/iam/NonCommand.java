package ru.spb.iam;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
// import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;


public class NonCommand {
    private Logger logger = LoggerFactory.getLogger(NonCommand.class);

    public String nonCommandExecute(Update update) {
        Message msg = update.getMessage();
        logger.debug(String.valueOf(update));
        Long chatId = msg.getChatId();
        String userName = Utils.getUserName(msg);

        logger.debug(String.format("Пользователь %s. Начата обработка сообщения \"%s\", не являющегося командой",
                userName, String.valueOf(msg)));

        if (msg.hasLocation()) return "Прислано местоположение";

        String answer = "Это нонкоманд заглушка";

 //       OpenWeatherMapClient openWeatherClient = new OpenWeatherMapClient(System.getenv("OPEN_WEATHER_TOKEN"));

//        final Weather weather = openWeatherClient
//                .currentWeather()
//                .single()
//                .byCityName("Minsk")
//                .language(Language.RUSSIAN)
//                .unitSystem(UnitSystem.METRIC)
//                .retrieve()
//                .asJava();
//
//        logger.debug(String.format("Погода в Минске: \"%s\"", String.valueOf(weather)));

        logger.debug(String.format("Пользователь %s. Завершена обработка сообщения \"%s\", не являющегося командой",
                userName, String.valueOf(msg)));
        return answer;
    }
}