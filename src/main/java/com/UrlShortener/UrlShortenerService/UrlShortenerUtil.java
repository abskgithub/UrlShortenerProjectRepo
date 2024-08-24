package com.UrlShortener.UrlShortenerService;

import java.util.Random;

public class UrlShortenerUtil {

    private static final String allBasic_Char62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random RANDOM = new Random();

    public static String generateShortUrl(String originalUrl) {
        StringBuilder shortUrl = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            shortUrl.append(allBasic_Char62.charAt(RANDOM.nextInt(allBasic_Char62.length())));
        }
        return shortUrl.toString();
    }
}