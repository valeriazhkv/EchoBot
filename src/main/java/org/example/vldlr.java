package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Основной класс Telegram-бота, обрабатывающий входящие обновления методом длинного поллинга.
 */
public class vldlr extends TelegramLongPollingBot {

    private final BotLogic botLogic = new BotLogic();

    /**
     * Возвращает имя бота из конфигурации.
     * @return имя бота
     */
    @Override
    public String getBotUsername() {
        return BotConfig.getBotUsername();
    }

    /**
     * Возвращает токен бота из конфигурации.
     * @return токен бота
     */
    @Override
    public String getBotToken() {
        return BotConfig.getBotToken();
    }

    /**
     * Обрабатывает входящие обновления (сообщения) от пользователей Telegram.
     * @param update объект обновления Telegram
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String userText = update.getMessage().getText();

            // Получаем универсальный текстовый ответ от бизнес-логики
            String responseText = botLogic.handleMessage(userText);

            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chatId));
            message.setText(responseText);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}