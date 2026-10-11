package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс для модульного (юнит) тестирования универсальной бизнес-логики {@link BotLogic}.
 */
class BotLogicTest {

    private BotLogic botLogic;

    @BeforeEach
    void setUp() {
        botLogic = new BotLogic();
    }

    @Test
    @DisplayName("Тест на обработку обычного текстового сообщения")
    void testHandleRegularMessage() {
        String response = botLogic.handleMessage("Привет, мир!");

        Assertions.assertEquals("Эхо-ответ: Привет, мир!", response);
    }

    @Test
    @DisplayName("Тест на обработку команды /start")
    void testHandleStartCommand() {
        String response = botLogic.handleMessage("/start");

        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.contains("Это кросс-платформенный бот-помощник."));
    }

    @Test
    @DisplayName("Тест на обработку команды /help")
    void testHandleHelpCommand() {
        String response = botLogic.handleMessage("/help");

        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.contains("Это кросс-платформенный бот-помощник."));
    }

    @Test
    @DisplayName("Тест на обработку сообщения из одних пробелов")
    void testHandleBlankMessage() {
        String response = botLogic.handleMessage("   ");

        Assertions.assertEquals("Получено пустое сообщение. Пожалуйста, введите текст.", response);
    }
}