
package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BotLogic {
    private final RandomProcess randomProcess = new RandomProcess();
    private final Map<Long, List<String>> activeGames = new HashMap<>();

    /**
     * Обрабатывает входящий текст и возвращает текстовый ответ.
     *
     * @param chatId идентификатор чата пользователя
     * @param text входящий текст
     * @return строка с ответом для пользователя
     */
    public String handleMessage(long chatId, String text) {
        if (text == null || text.isBlank()) {
            return "Получено пустое сообщение. Пожалуйста, введите текст.";
        }

        String trimmed = text.trim();

        if (trimmed.equals("/start")) {
            return "Здравствуйте! Это эмодзи бот. Вам нужно угадать правильный порядок эмодзи. Используйте /help чтобы увидеть список команд.";
        }

        if (trimmed.equals("/help")) {
            return "Доступные команды:\n" +
                    "/start — Запустить бота\n" +
                    "/play — Начать игру\n" +
                    "/giveup — Сдаться и увидеть правильный порядок";
        }
        if (trimmed.equals("/play")) {
            if (activeGames.containsKey(chatId)) {
                return "Игра уже идёт. Присылайте ответ или сдавайтесь: /giveup";
            }

            List<String> emojiSecret = randomProcess.getShuffledArray();
            activeGames.put(chatId, emojiSecret);

            String formattedEmoji = String.join(" ", emojiSecret);

            return "Набор: " + "\uD83C\uDF4E \uD83D\uDE80 \uD83D\uDC31 \uD83C\uDFB8 \uD83C\uDF19" +
                    "\nЯ загадал их порядок. Пришлите все пять эмодзи " +
                    "в том порядке, который считаете верным.";
        }


        if (trimmed.equals("/giveup")) {

            if (!activeGames.containsKey(chatId)) {
                return "Игра не начата. Начните новую: /play";
            }

            List<String> emojiSecret = activeGames.remove(chatId);

            String formattedEmoji = String.join(" ", emojiSecret);

            return "Вы сдались. Загаданный порядок: " +
                    formattedEmoji + "\nНовая игра: /play";
        }
        if (!activeGames.containsKey(chatId)) {
            return "Игра не начата. Начните новую: /play";
        }

        List<String> emojiSecret = activeGames.get(chatId);

        String validationResult =
                randomProcess.validateOrder(trimmed, emojiSecret);


        if (validationResult.contains("Вы победили!")) {
            activeGames.remove(chatId);
        }
        return validationResult;
    }
}
