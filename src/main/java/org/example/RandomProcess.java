package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Класс для управления игровым процессом с эмодзи-символами.
 * Отвечает за генерацию случайного порядка символов и сравнение ответов пользователя.
 */
public class RandomProcess {
    /** Исходная строка с базовым набором эмодзи. */
    private static final String BASE_SYMBOLS = "\uD83C\uDF4E \uD83D\uDE80 \uD83D\uDC31 \uD83C\uDFB8 \uD83C\uDF19";

    /**
     * Создает массив строк, содержащий базовые эмодзи в случайном порядке.
     * Очищает строку от пробелов и безопасно перемешивает символы Юникода.
     *
     * @return массив строк {@code String[]}, где каждый элемент — одно случайное эмодзи
     */
    public String[] getShuffledArray() {
        String cleanBase = BASE_SYMBOLS.replace(" ", "");
        List<String> characters = new ArrayList<>(Arrays.asList(cleanBase.split(" ")));

        Collections.shuffle(characters);

        return characters.toArray(new String[0]);
    }

    /**
     * Пошагово проверяет ввод пользователя, считает количество совпадений
     * и возвращает готовый текст ответа.
     *
     * @param text строка-ответ, полученная от пользователя
     * @param botShuffledArray массив с правильным порядком
     * @return готовая строка {@code String} с результатом
     */
    public String validateOrder(String text, String[] botShuffledArray) {
        //Чистим строку от пробелов
        String finalText = text.replace(" ", "");
        String[] userSymbols = finalText.split("");

        //Проверяем на посторонние символы
        Set<String> allowedSymbols = new HashSet<>(Arrays.asList(botShuffledArray));
        for (String userSymbol : userSymbols) {
            if (!allowedSymbols.contains(userSymbol)) {
                return "В вашем сообщении содержатся посторонние символы. Используйте только заданные эмодзи!";
            }
        }

        //Проверяем на количество эмодзи
        if (userSymbols.length != botShuffledArray.length) {
            return "Нужно прислать все эмодзи из набора, каждый по одному разу";
        }

        //Считаем количество совпадений
        int corrCount = 0;
        for (int i = 0; i < botShuffledArray.length; i++) {
            if (userSymbols[i].equals(botShuffledArray[i])) {
                corrCount++;
            }
        }

        //Формируем финальный ответ на основе счетчика
        int totCount = botShuffledArray.length;

        if (corrCount == totCount) {
            return corrCount + " из " + totCount + "! Вы победили! Новая игра: /play";
        } else {
            return corrCount + "" + totCount;
        }
    }
}
