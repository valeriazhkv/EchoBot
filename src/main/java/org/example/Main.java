package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * Главный класс приложения для запуска Telegram-бота.
 */
public class Main {
    /**
     * Точка входа в приложение. Регистрирует и запускает бота.
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new vldlr());
            System.out.println("Бот успешно запущен!");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}