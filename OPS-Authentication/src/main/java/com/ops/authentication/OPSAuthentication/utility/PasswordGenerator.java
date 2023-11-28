package com.ops.authentication.OPSAuthentication.utility;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {

    private static final String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMERIC_CHARACTERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-=_+[]{}|;:'\",.<>/?";

    public static String generateRandomPassword(int length) {
        StringBuilder password = new StringBuilder();
        Random random = new SecureRandom();

        // Use at least one character from each character set
        password.append(getRandomChar(LOWERCASE_CHARACTERS, random));
        password.append(getRandomChar(UPPERCASE_CHARACTERS, random));
        password.append(getRandomChar(NUMERIC_CHARACTERS, random));
        password.append(getRandomChar(SPECIAL_CHARACTERS, random));

        // Use remaining characters from a combined set
        String combinedCharacters = LOWERCASE_CHARACTERS + UPPERCASE_CHARACTERS + NUMERIC_CHARACTERS + SPECIAL_CHARACTERS;
        for (int i = 4; i < length; i++) {
            password.append(getRandomChar(combinedCharacters, random));
        }

        // Shuffle the characters to enhance randomness
        char[] passwordArray = password.toString().toCharArray();
        for (int i = passwordArray.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = passwordArray[index];
            passwordArray[index] = passwordArray[i];
            passwordArray[i] = temp;
        }

        return new String(passwordArray);
    }

    private static char getRandomChar(String characterSet, Random random) {
        int index = random.nextInt(characterSet.length());
        return characterSet.charAt(index);
    }
}
