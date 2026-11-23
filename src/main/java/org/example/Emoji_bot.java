package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Основной класс Telegram-бота, обрабатывающий входящие обновления методом длинного поллинга.
 */
public class Emoji_bot extends TelegramLongPollingBot {

    private final BotLogic botLogic = new BotLogic();
    private final BotConfig config = new BotConfig();

    @Override
    public String getBotUsername() {
        return config.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String userText = update.getMessage().getText();

            // Получаем универсальный текстовый ответ от бизнес-логики
            String responseText = botLogic.handleMessage(chatId, userText);

            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chatId));
            message.setText(responseText);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                System.err.println("Не удалось отправить сообщение в чат " + chatId + ": " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}