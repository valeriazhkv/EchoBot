package org.example;

/**
 * Универсальный класс бизнес-логики для обработки текстовых сообщений.
 */
public class BotLogic {

    /** Текст справки и приветствия. */
    private static final String HELP_TEXT = "Здравствуйте! Это кросс-платформенный бот-помощник.\n" +
            "Отправьте мне любое сообщение, и я обработаю его.";

    /**
     * Обрабатывает входящий текст и возвращает текстовый ответ.
     * @param text входящий текст
     * @return строка с ответом для пользователя
     */
    public String handleMessage(String text) {
        // 1. Проверка на пустое сообщение или пробелы
        if (text == null || text.isBlank()) {
            return "Получено пустое сообщение. Пожалуйста, введите текст.";
        }

        String trimmed = text.trim();

        // 2. Проверка на команды /start и /help
        if ("/start".equals(trimmed) || "/help".equals(trimmed)) {
            return HELP_TEXT;
        }

        // 3. Ответ на обычный текст
        return "Эхо-ответ: " + trimmed;
    }
}