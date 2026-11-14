package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

/**
 * Класс для тестов логики игры с эмодзи {@link RandomProcess}.
 */
public class RandomProcessTest {
    /** Экземпляр тестируемой логики. */
    private RandomProcess process;

    /** Фиксированный правильный порядок для проверки. */
    private List<String> correctOrder;

    /**
     * Подготавливает окружение перед каждым тестом.
     */
    @BeforeEach
    void setUp() {
        process = new RandomProcess();
        correctOrder = List.of("🍎", "🚀", "🐱", "🎸", "🌙");
    }

    /**
     * Тест для проверки на полное совпадение.
     */
    @Test
    void testValidateOrder_Win() {
        String result = process.validateOrder("🍎🚀🐱🎸🌙", correctOrder);
        Assertions.assertTrue(result.contains("5 из 5"));
        Assertions.assertTrue(result.contains("победили"));
    }

    /**
     * Тест для проверки на полное совпадение с пробелами.
     */
    @Test
    void testValidateOrder_Spaces_Win() {
        String result = process.validateOrder("🍎 🚀 🐱 🎸 🌙", correctOrder);
        Assertions.assertTrue(result.contains("победили"));
    }

    /**
     * Тест для проверки на неполное совпадение
     */
    @Test
    void testValidateOrder_Not_Win() {
        String result = process.validateOrder("🍎🐱🚀🎸🌙", correctOrder);
        Assertions.assertEquals("3 из 5", result);
    }

    /**
     * Тест на посторонние символы
     */
    @Test
    void testValidateOrder_Symbols() {
        String result = process.validateOrder("🍎🚀🐱🎸🍕", correctOrder);
        Assertions.assertTrue(result.contains("посторонние символы"));
    }

    /**
     * Тест на неполный набор
     */
    @Test
    void testValidateOrder_Length() {
        String result = process.validateOrder("🍎🚀🐱", correctOrder);
        Assertions.assertTrue(result.contains("Нужно прислать все эмодзи"));
    }

    /**
     * Тест на дубликаты
     */
    @Test
    void testValidateOrder_Duplicate() {
        // Длина верная, но есть дубликат 🍎 и нет 🎸
        String result = process.validateOrder("🍎🍎🚀🐱🌙", correctOrder);
        Assertions.assertTrue(result.contains("только один раз"));
    }

    /**
     * Тест для проверки корректного перемешивания эмодзи (суррогатные пары сохранились)
     */
    @Test
    void testGetShuffledArray_Size() {
        List<String> shuffled = process.getShuffledArray();
        Assertions.assertEquals(5, shuffled.size());
    }

    /**
     * Тест для проверки на добавление неопознанных символов при перемешивании
     */
    @Test
    void testGetShuffledArray_ContainsOnlyBaseSymbols() {
        List<String> shuffled = process.getShuffledArray();
        for (String symbol : shuffled) {
            Assertions.assertTrue(correctOrder.contains(symbol), "Найден неизвестный символ: " + symbol);
        }
    }
}
