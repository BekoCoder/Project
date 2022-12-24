package jk.uz.pdp.model;

import java.util.UUID;

public class Message {
    private UUID sender;
    private UUID receiver;
    private String message;
    private long time;
    private boolean isRead;

    public Message() {
    }

    public Message(UUID sender, UUID receiver, String message, long time) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.time = time;
    }

    public Message(UUID sender, UUID receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public UUID getSender() {
        return sender;
    }

    public void setSender(UUID sender) {
        this.sender = sender;
    }

    public UUID getReceiver() {
        return receiver;
    }

    public void setReceiver(UUID receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}

/*
* // Men
*           Hi
* Hello
*           How are you?
* Nice, and you?
*
* // Omad aka
* Hi
*       Hello
* How are you?
*       Nice, and you ?
*
*
* */
