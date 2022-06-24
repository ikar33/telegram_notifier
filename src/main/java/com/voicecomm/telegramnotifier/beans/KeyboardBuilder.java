package com.voicecomm.telegramnotifier.beans;

import org.apache.camel.Exchange;
import org.apache.camel.component.telegram.model.IncomingMessage;
import org.apache.camel.component.telegram.model.InlineKeyboardButton;
import org.apache.camel.component.telegram.model.OutgoingTextMessage;
import org.apache.camel.component.telegram.model.ReplyKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

public class KeyboardBuilder {

    private OutgoingTextMessage userKeyboardsOutgoingMessage = new OutgoingTextMessage();
    private OutgoingTextMessage adminKeyboardsOutgoingMessage = new OutgoingTextMessage();

    public KeyboardBuilder(List<String> userkeyboard, List<String> adminkeyboard, String keyboardsTitle) {
        this.userKeyboardsOutgoingMessage.setText(keyboardsTitle);
        this.adminKeyboardsOutgoingMessage.setText(keyboardsTitle);

        List<InlineKeyboardButton> userInlineKeyboardButtons = new ArrayList<>();
        List<InlineKeyboardButton> adminInlineKeyboardButtons = new ArrayList<>();
        for (String buttonName : userkeyboard) {
            userInlineKeyboardButtons.add(InlineKeyboardButton.builder().text(buttonName).build());
        }
        for (String buttonName : adminkeyboard) {
            adminInlineKeyboardButtons.add(InlineKeyboardButton.builder().text(buttonName).build());
        }
        buildUserKeyboardsMessage(userInlineKeyboardButtons);
        buildAdminKeyboardsMessage(adminInlineKeyboardButtons);
    }

    public void getKeyBoard(String mode, Exchange exchange) {
        if (mode == null || "USER".equals(mode)) {
            userKeyboardsOutgoingMessage.setChatId(exchange.getIn().getHeader("incomingMessage", IncomingMessage.class).getChat().getId());
            exchange.getIn().setBody(userKeyboardsOutgoingMessage);
        } else if ("ADMIN".equals(mode)) {
            adminKeyboardsOutgoingMessage.setChatId(exchange.getIn().getHeader("incomingMessage", IncomingMessage.class).getChat().getId());
            exchange.getIn().setBody(adminKeyboardsOutgoingMessage);
        }
    }

    private void buildUserKeyboardsMessage(List<InlineKeyboardButton> userInlineKeyboardButtons) {
        ReplyKeyboardMarkup userReplyMarkup = ReplyKeyboardMarkup.builder().keyboard()
                .addOneRowByEachButton(userInlineKeyboardButtons)
                .close()
                .oneTimeKeyboard(false)
                .build();
        userKeyboardsOutgoingMessage.setReplyKeyboardMarkup(userReplyMarkup);
    }

    private void buildAdminKeyboardsMessage(List<InlineKeyboardButton> adminInlineKeyboardButtons) {
        ReplyKeyboardMarkup adminReplyMarkup = ReplyKeyboardMarkup.builder().keyboard()
                .addOneRowByEachButton(adminInlineKeyboardButtons)
                .close()
                .oneTimeKeyboard(false)
                .build();
        adminKeyboardsOutgoingMessage.setReplyKeyboardMarkup(adminReplyMarkup);
    }

}
