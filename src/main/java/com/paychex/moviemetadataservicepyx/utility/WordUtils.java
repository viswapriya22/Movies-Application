package com.paychex.moviemetadataservicepyx.utility;

public class WordUtils {
    public static String capitalizeTitle(String title) {
        String[] words = title.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
        }
        return String.join(" ", words);
    }
}
