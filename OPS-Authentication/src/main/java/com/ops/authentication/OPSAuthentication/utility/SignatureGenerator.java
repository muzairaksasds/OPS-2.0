package com.ops.authentication.OPSAuthentication.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignatureGenerator {

    public static String generateSignature(String userType, String username, String userpassword,
                                           String apikey, String apisecret, String msisdn) throws NoSuchAlgorithmException {
        StringBuilder signature = new StringBuilder();

        appendField(signature, "userType", userType);
        appendField(signature, "username", username);
        appendField(signature, "userpassword", userpassword);
        appendField(signature, "apikey", apikey);
        appendField(signature, "apisecret", apisecret);
        appendField(signature, "msisdn", msisdn);
       // appendField(signature, "qrcode", qrcode);

        System.out.println(signature.toString());
        return generateMD5Hash(signature.toString());
    }
    private static void appendField(StringBuilder signature, String fieldName, String fieldValue) {
        if (fieldValue != null && !fieldValue.isEmpty()) {
            if (!signature.isEmpty()) {
                signature.append(":");
            }
            signature.append(fieldName).append(fieldValue);
        }
    }

    public static String generateMD5Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        byte[] digest = md.digest();

        // Convert the byte array to a hex string
        StringBuilder hexString = new StringBuilder();
        for (byte b : digest) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }
}