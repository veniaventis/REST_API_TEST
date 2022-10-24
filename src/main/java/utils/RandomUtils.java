package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {
    public static String getRandomString(int randomLength) {
        return RandomStringUtils.randomAlphanumeric(randomLength);
    }
}