package org.example.util;

import com.github.javafaker.Faker;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class RandomDataUtils {

    private static final Faker faker = new Faker();

    public static String genRandomPassword(int minimumLength, int maximumLength) {
        return faker.internet().password(minimumLength, maximumLength);
    }

    public static String genRandomUsername() {
        return faker.name().username();
    }

    public static String genRandomCategory() {
        return faker.commerce().department();
    }

    public static String genRandomSentence(int wordsCount) {
        return faker.lorem().sentence(wordsCount);
    }

    public static String genRandomString(int numberOfLetters) {
        return randomAlphabetic(numberOfLetters);
    }

    public static String genRandomString(int minLength, int maxLength) {
        return randomAlphabetic(minLength, maxLength);
    }

    public static String genRandomNumbers(int minNumber, int maxNumber) {
        return String.valueOf(faker.number().numberBetween(minNumber, maxNumber));
    }
}
