package org.example;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Класс для управления игровым процессом с эмодзи-символами.
 * Отвечает за генерацию случайного порядка символов и сравнение ответов пользователя.
 */
public class RandomProcess {
    /** Исходная строка с базовым набором эмодзи. */
    private static final String BASE_SYMBOLS = "\uD83C\uDF4E \uD83D\uDE80 \uD83D\uDC31 \uD83C\uDFB8 \uD83C\uDF19";

    /**
     * Создает список, содержащий базовые эмодзи,
     * и перемешивает их в произвольном порядке
     *
     * @return список {@code List<String>}, где каждый элемент — одно случайное эмодзи
     */
    public List<String> getShuffledArray() {
        List<String> characters = BASE_SYMBOLS.codePoints()
                .filter(cp -> cp != ' ')
                .mapToObj(cp -> new String(Character.toChars(cp)))
                .collect(Collectors.toList());

        Collections.shuffle(characters);
        return characters;
    }

    /**
     * Пошагово проверяет ввод пользователя, считает количество совпадений
     * и возвращает готовый текст ответа.
     *
     * @param text строка-ответ, полученная от пользователя
     * @param botShuffledArray список с правильным порядком
     * @return готовая строка {@code String} с результатом
     */
    public String validateOrder(String text, List<String> botShuffledArray) {
        //Разбиваем ввод от пользователя на отдельные символы
        List<String> userSymbols = text.codePoints()
                .filter(cp -> cp != ' ')
                .mapToObj(cp -> new String(Character.toChars(cp)))
                .collect(Collectors.toList());

        //Проверяем на посторонние символы
        Set<String> allowedSymbols = new HashSet<>(botShuffledArray);
        for (String userSymbol : userSymbols) {
            if (!allowedSymbols.contains(userSymbol)) {
                return "В вашем сообщении содержатся посторонние символы. Используйте только заданные эмодзи!";
            }
        }

        //Проверяем на количество эмодзи
        if (userSymbols.size() != botShuffledArray.size()) {
            return "Нужно прислать все эмодзи из набора, каждый по одному разу";
        }

        Set<String> uniqueUserSymbols = new HashSet<>(userSymbols);
        if (uniqueUserSymbols.size() != userSymbols.size()) {
            return "Каждый эмодзи можно использовать только один раз!";
        }

        //Считаем количество совпадений
        int corrCount = 0;
        for (int i = 0; i < botShuffledArray.size(); i++) {
            if (userSymbols.get(i).equals(botShuffledArray.get(i))) {
                corrCount++;
            }
        }

        //Формируем финальный ответ на основе счетчика
        int totCount = botShuffledArray.size();

        if (corrCount == totCount) {
            return corrCount + " из " + totCount + "! Вы победили! Новая игра: /play";
        } else {
            return corrCount + " из " + totCount;
        }
    }
}