package com.voicecomm.telegramnotifier.utils;

import org.apache.camel.Exchange;

import java.util.Random;

public class Utils {

    public String generatePassword(Integer length, Exchange exchange) {
        Integer defaultPasswordLength = 5;

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length != null && length > 4 ? length : defaultPasswordLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();


        return generatedString;
    }

    public void getLastSubstring(Integer substringLength, Exchange exchange) {
        String message = exchange.getIn().getBody(String.class);
        if (message != null && message.length() > substringLength)
            exchange.getIn().setBody(message.substring(message.length() - substringLength));
    }

}
