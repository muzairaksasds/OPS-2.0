package com.ops.authentication.OPSAuthentication.utility;

public class SignatureGenerator {

    public static String generateSignature(String userType, String username, String userpassword,
                                           String apikey, String apisecret, String msisdn, String qrcode) {
        StringBuilder signature = new StringBuilder();

        appendField(signature, "userType", userType);
        appendField(signature, "username", username);
        appendField(signature, "userpassword", userpassword);
        appendField(signature, "apikey", apikey);
        appendField(signature, "apisecret", apisecret);
        appendField(signature, "msisdn", msisdn);
        appendField(signature, "qrcode", qrcode);

        return signature.toString();
    }
    private static void appendField(StringBuilder signature, String fieldName, String fieldValue) {
        if (fieldValue != null && !fieldValue.isEmpty()) {
            if (!signature.isEmpty()) {
                signature.append(":");
            }
            signature.append(fieldName).append(fieldValue);
        }
    }
}