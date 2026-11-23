package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;


/**
 *тесты для {@link BotLogic}.
 */
class BotLogicTest {

    private BotLogic botLogic;
    private static final long CHAT_ID = 123L;

    @BeforeEach
    void setUp() {
        botLogic = new BotLogic();
    }


    /**
     * Проверяет команду {@code /start}
     */
    @Test
    void testStartCommand() {
        String response = botLogic.handleMessage(CHAT_ID, "/start");
        Assertions.assertEquals("Здравствуйте! Это эмодзи бот. " +
                "Вам нужно угадать правильный порядок эмодзи. " +
                "Используйте /help чтобы увидеть список команд." ,response);
    }


    /**
     * Проверяет команду {@code /play}
     */
    @Test
    void testPlayCommand() {
        String response = botLogic.handleMessage(CHAT_ID, "/play");
        Assertions.assertEquals("Набор: " + "\uD83C\uDF4E \uD83D\uDE80 \uD83D\uDC31 \uD83C\uDFB8 \uD83C\uDF19" +
                "\nЯ загадал их порядок. Пришлите все пять эмодзи " +
                "в том порядке, который считаете верным." ,response);
    }


    /**
     * Проверяет команду {@code /giveup}
     */
    @Test
    void testGiveUpCommand() {
        List<String> fixed = List.of("🍎", "🚀", "🐱", "🎸", "🌙");

        RandomProcess stub = new RandomProcess() {
            @Override
            public List<String> getShuffledArray() {
                return fixed;
            }
        };

        BotLogic bot = new BotLogic(stub);

        bot.handleMessage(CHAT_ID, "/play");
        String response = bot.handleMessage(CHAT_ID, "/giveup");

        Assertions.assertEquals("Вы сдались. Загаданный порядок: " +
                        String.join(" ", fixed) + "\nНовая игра: /play", response);
    }


    /**
     * Проверяет обработку пустого сообщения
     */
    @Test
    void testBlankMessage() {
        String response = botLogic.handleMessage(CHAT_ID, "   ");
        Assertions.assertEquals("Получено пустое сообщение. Пожалуйста, введите текст.", response);
    }

    /**
     * Проверяет команду {@code /help}
     */
    @Test
    void testHelpCommand() {
        String response = botLogic.handleMessage(CHAT_ID, "/help");
        Assertions.assertEquals("Доступные команды:\n" +
                "/start — Запустить бота\n" +
                "/play — Начать игру\n" +
                "/giveup — Сдаться и увидеть правильный порядок" ,response);
    }
}