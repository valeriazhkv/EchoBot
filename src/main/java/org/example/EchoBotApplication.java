package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * Главный класс Spring Boot приложения.
 * Запускает контекст Spring, подключает базу данных и запускает бота.
 */
@SpringBootApplication
public class EchoBotApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(EchoBotApplication.class, args);

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

            vldlr bot = context.getBean(vldlr.class);

            botsApi.registerBot(bot);
            System.out.println("Бот успешно запущен и подключен к базе данных!");

        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println("Ошибка при запуске бота: " + e.getMessage());
        }
    }
}
