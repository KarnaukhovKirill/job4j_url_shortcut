package ru.job4j.shortcut.util;

public final class GeneratorRandomString {
    private static final String POSSIBLECHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String ofPassword(int value) {
        return generate(value);
    }

    public static String ofCode(int value) {
        return generate(value);
    }

    private static String generate(int numberOfChars) {
        String rsl = "";
        for (int i = 0; i < numberOfChars; i++) {
            rsl += POSSIBLECHARS.charAt((int) Math.floor(Math.random() * POSSIBLECHARS.length()));
        }
        return rsl;
    }
}
