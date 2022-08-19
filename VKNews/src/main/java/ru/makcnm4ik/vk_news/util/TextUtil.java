package ru.makcnm4ik.vk_news.util;

import java.util.regex.Pattern;

public class TextUtil {
    public static boolean isUrl(String string) {
        return isFullUrl(string) || isShortUrl(string);
    }

    public static boolean isFullUrl(String string) {
        return Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
                .matcher(string)
                .find();
    }

    public static boolean isShortUrl(String string) {
        return Pattern.compile("^[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$")
                .matcher(string)
                .find();
    }

    // TODO Убрать группы в regex для околооптимизации XD (Чтобы IDE не жаловалась)
}
