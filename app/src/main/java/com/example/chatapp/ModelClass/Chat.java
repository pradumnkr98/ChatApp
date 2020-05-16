package com.example.chatapp.ModelClass;

public class Chat {

    private String receiver;
    private String sender;
    private String message;
    private boolean isSeen;

    public Chat(String sender, String receiver, String message, boolean isSeen) {
        this.receiver = receiver;
        this.sender = sender;
        this.message = message;
        this.isSeen = isSeen;
    }

    public Chat() {
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }
}
