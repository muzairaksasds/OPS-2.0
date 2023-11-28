package com.ops.authentication.OPSAuthentication.utility;

import java.security.SecureRandom;

public class ApiKeyGenerator {

    private static final String API_KEY_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int API_KEY_LENGTH = 32; // You can adjust the length as needed

    public static String generateRandomApiKey() {
        StringBuilder apiKey = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < API_KEY_LENGTH; i++) {
            int index = random.nextInt(API_KEY_CHARACTERS.length());
            apiKey.append(API_KEY_CHARACTERS.charAt(index));
        }

        return apiKey.toString();
    }
}
