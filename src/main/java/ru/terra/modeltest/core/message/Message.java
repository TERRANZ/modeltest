package ru.terra.modeltest.core.message;

public abstract class Message {
    private String senderUID;

    public Message(String senderUID) {
        this.senderUID = senderUID;
    }

    public String getSenderUID() {
        return senderUID;
    }

    public void setSenderUID(String senderUID) {
        this.senderUID = senderUID;
    }
}
