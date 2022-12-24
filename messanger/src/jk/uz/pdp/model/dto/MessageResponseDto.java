package jk.uz.pdp.model.dto;

import java.util.UUID;

public class MessageResponseDto {
    private UUID whoIsMessage;
    private String messageText;
    private boolean currentUserIsSender;

    public MessageResponseDto() {
    }

    public MessageResponseDto(String messageText, boolean currentUserIsSender) {
        this.messageText = messageText;
        this.currentUserIsSender = currentUserIsSender;
    }

    public MessageResponseDto(UUID whoIsMessage, String messageText, boolean currentUserIsSender) {
        this.whoIsMessage = whoIsMessage;
        this.messageText = messageText;
        this.currentUserIsSender = currentUserIsSender;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public boolean isCurrentUserIsSender() {
        return currentUserIsSender;
    }

    public void setCurrentUserIsSender(boolean currentUserIsSender) {
        this.currentUserIsSender = currentUserIsSender;
    }

    public UUID getWhoIsMessage() {
        return whoIsMessage;
    }

    public void setWhoIsMessage(UUID whoIsMessage) {
        this.whoIsMessage = whoIsMessage;
    }
}
