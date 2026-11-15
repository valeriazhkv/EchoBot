package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BotLogicTest {

    private BotLogic botLogic;
    private static final long CHAT_ID = 123L;

    @BeforeEach
    void setUp() {
        botLogic = new BotLogic();
    }

    @Test
    void testStartCommand() {
        String response = botLogic.handleMessage(CHAT_ID, "/start");
        Assertions.assertTrue(response.contains("Здравствуйте! Это эмодзи бот"));
    }

    @Test
    void testPlayCommand() {
        String response = botLogic.handleMessage(CHAT_ID, "/play");
        Assertions.assertTrue(response.startsWith("Набор: "));
    }

    @Test
    void testGiveUpCommand() {
        botLogic.handleMessage(CHAT_ID, "/play");
        String response = botLogic.handleMessage(CHAT_ID, "/giveup");
        Assertions.assertTrue(response.startsWith("Вы сдались."));
    }

    @Test
    void testBlankMessage() {
        String response = botLogic.handleMessage(CHAT_ID, "   ");
        Assertions.assertEquals("Получено пустое сообщение. Пожалуйста, введите текст.", response);
    }
    @Test
    void testHelpCommand() {
        String response = botLogic.handleMessage(CHAT_ID, "/help");
        Assertions.assertTrue(response.contains("Доступные команды:"));
    }
}