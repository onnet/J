package ru.spb.iam.telebot;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.Coordinate;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.SQLException;

import static ru.spb.iam.db.Utils.*;


public class NonCommand {
    private Logger logger = LoggerFactory.getLogger(NonCommand.class);

    public String nonCommandExecute(Update update) throws SQLException {
        Message msg = update.getMessage();
        logger.debug(String.valueOf(update));
        Long chatId = msg.getChatId();
        String userName = Utils.getUserName(msg);
        String answer = "Не знаю что ответить на это. Обратитесь к Алисе.. \uD83D\uDE25";

        logger.debug(String.format("Пользователь %s. Начата обработка сообщения \"%s\", не являющегося командой",
                userName, msg));

        if (msg.hasLocation()) {
            logger.debug(String.format("Пользователь %s. Запрос погоды по местоположению: \"%s\"", userName, msg.getLocation()));
            if (maybeUserExists(msg.getFrom().getId())) addLocation(msg.getFrom().getId(), msg.getLocation().getLatitude(), msg.getLocation().getLongitude());
            answer = String.valueOf(getWeatherByLL(msg.getLocation().getLatitude(), msg.getLocation().getLongitude()));
        }
        logger.debug(String.format("Пользователь %s. Завершена обработка сообщения \"%s\", не являющегося командой",
                userName, msg));

        return answer;
    }

    private Weather getWeatherByLL(Double latitude, Double longitude) {

        OpenWeatherMapClient openWeatherClient = new OpenWeatherMapClient(System.getenv("OPEN_WEATHER_TOKEN"));

        final Weather weather = openWeatherClient
                .currentWeather()
                .single()
                .byCoordinate(Coordinate.of(latitude, longitude))
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();

        logger.debug(String.format("Погода в по координатам: \"%s\"", weather));
        logger.debug(String.format("Координаты: \"%s\"", Coordinate.of(53.54, 27.34)));

        return weather;

    }

}