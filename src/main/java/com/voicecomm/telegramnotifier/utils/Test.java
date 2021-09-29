package com.voicecomm.telegramnotifier.utils;

import org.apache.camel.Exchange;
import org.apache.camel.component.telegram.model.IncomingMessage;
import org.apache.camel.component.telegram.model.InlineKeyboardButton;
import org.apache.camel.component.telegram.model.OutgoingTextMessage;
import org.apache.camel.component.telegram.model.ReplyKeyboardMarkup;

import java.util.Arrays;

public class Test {

    public void test(Exchange exchange) {
        //System.out.println(exchange.getIn().getBody());
        System.out.println(" ");
    }

    public void great(Exchange exchange) {
        //System.out.println(exchange.getIn().getBody());
        IncomingMessage incomingMessage = exchange.getIn().getBody(IncomingMessage.class);
        exchange.getIn().setBody("Hello, " + incomingMessage.getFrom().getFirstName() + " CHAT_ID=" + incomingMessage.getChat().getId());
    }

    public void getLastSubstring(Integer substringLength, Exchange exchange) {
        String message = exchange.getIn().getBody(String.class);
        if (message != null && message.length() > substringLength)
            exchange.getIn().setBody(message.substring(message.length() - substringLength));
    }

    public void keyBoard(Exchange exchange) {
        OutgoingTextMessage msg = new OutgoingTextMessage();
        msg.setText("Choose one option!");

        InlineKeyboardButton buttonOptionOneI = InlineKeyboardButton.builder().text("Sabre.pl log").build();

        InlineKeyboardButton buttonOptionOneII = InlineKeyboardButton.builder().text("Short tasks statistic").build();

        InlineKeyboardButton buttonOptionTwoI = InlineKeyboardButton.builder().text("Short voice calls statistic").build();

        ReplyKeyboardMarkup replyMarkup = ReplyKeyboardMarkup.builder().keyboard()
                .addRow(Arrays.asList(buttonOptionOneI, buttonOptionOneII, buttonOptionTwoI))
                .addRow(Arrays.asList(buttonOptionTwoI))
                .close()
                .oneTimeKeyboard(true)
                .build();

        msg.setReplyKeyboardMarkup(replyMarkup);
        exchange.getIn().setBody(msg);
    }
}
