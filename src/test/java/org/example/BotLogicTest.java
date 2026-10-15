package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Класс для модульного (юнит) тестирования универсальной бизнес-логики {@link BotLogic}.
 */
class BotLogicTest {

    /** Ожидаемый полный текст справки и приветствия. */
    private static final String EXPECTED_HELP_TEXT = "Здравствуйте! Это кросс-платформенный бот-помощник.\n" +
            "Отправьте мне любое сообщение, и я обработаю его.";

    /** Экземпляр тестируемой бизнес-логики, создаётся заново перед каждым тестом. */
    private BotLogic botLogic;

    /**
     * Подготавливает окружение перед каждым тестом: создаёт новый объект {@link BotLogic}.
     */
    @BeforeEach
    void setUp() {
        botLogic = new BotLogic();
    }

    /**
     * Тест на обработку обычного текстового сообщения.
     * Проверяет, что бот возвращает эхо-ответ с исходным текстом.
     */
    @Test
    void testHandleRegularMessage() {
        String response = botLogic.handleMessage("Привет, мир!");
        Assertions.assertEquals("Эхо-ответ: Привет, мир!", response);
    }

    /**
     * Тест на обработку команды /start.
     * Проверяет, что в ответ возвращается полный текст приветствия.
     */
    @Test
    void testHandleStartCommand() {
        String response = botLogic.handleMessage("/start");
        Assertions.assertEquals(EXPECTED_HELP_TEXT, response);
    }

    /**
     * Тест на обработку команды /help.
     * Проверяет, что в ответ возвращается полный текст справки.
     */
    @Test
    void testHandleHelpCommand() {
        String response = botLogic.handleMessage("/help");
        Assertions.assertEquals(EXPECTED_HELP_TEXT, response);
    }

    /**
     * Тест на обработку сообщения, состоящего только из пробелов.
     * Проверяет, что бот сообщает о пустом сообщении.
     */
    @Test
    void testHandleBlankMessage() {
        String response = botLogic.handleMessage("   ");
        Assertions.assertEquals("Получено пустое сообщение. Пожалуйста, введите текст.", response);
    }
}