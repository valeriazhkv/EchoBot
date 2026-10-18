package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Класс конфигурации бота для загрузки параметров из файла свойств.
 * Файл {@code config.properties} должен лежать в папке resources.
 */
public class BotConfig {

    /** Набор свойств, загруженных из файла {@code config.properties}. */
    private static final Properties props = new Properties();

    /**
     * Статический блок инициализации: один раз при загрузке класса читает
     * {@code config.properties} из ресурсов.
     */
    static {
        try (InputStream input = BotConfig.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("config.properties не найден в resources!");
            }
            props.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить config.properties", e);
        }
    }

    /**
     * Возвращает токен бота из конфигурации.
     * @return токен бота (значение свойства {@code bot.token})
     */
    public static String getBotToken() {
        return props.getProperty("bot.token");
    }

    /**
     * Возвращает имя пользователя бота из конфигурации.
     * @return имя бота (значение свойства {@code bot.username})
     */
    public static String getBotUsername() {
        return props.getProperty("bot.username");
    }
}