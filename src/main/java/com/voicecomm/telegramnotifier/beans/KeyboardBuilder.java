package com.voicecomm.telegramnotifier.beans;

import org.apache.camel.Exchange;
import org.apache.camel.component.telegram.model.InlineKeyboardButton;
import org.apache.camel.component.telegram.model.OutgoingTextMessage;
import org.apache.camel.component.telegram.model.ReplyKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

public class KeyboardBuilder {

    private List<String> parameters;
    private OutgoingTextMessage keyboardsOutgoingMessage = new OutgoingTextMessage();
    private boolean keyBoardCreated;

    public KeyboardBuilder(List<String> parameters, String keyboardsTitle) {
        this.parameters = parameters;
        this.keyboardsOutgoingMessage.setText(keyboardsTitle);
    }

    public void getKeyBoard(Exchange exchange) {
        if(!keyBoardCreated) {
            buildKeyboardsMessage();
            exchange.getIn().setBody(keyboardsOutgoingMessage);
            keyBoardCreated = true;
        }
    }
    private void buildKeyboardsMessage() {
        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
        if (this.parameters != null) {
            for (String buttonName : parameters) {
                inlineKeyboardButtons.add(InlineKeyboardButton.builder().text(buttonName).build());
            }
        }
        ReplyKeyboardMarkup replyMarkup = ReplyKeyboardMarkup.builder().keyboard()
                .addRow(inlineKeyboardButtons)
                .close()
                .oneTimeKeyboard(false)
                .build();
        keyboardsOutgoingMessage.setReplyKeyboardMarkup(replyMarkup);
    }

}
