package ru.terra.modeltest.core.message;

public abstract class Message {
    private String senderUID;
    private String targetUID;

    public Message(String senderUID) {
        this.senderUID = senderUID;
    }

    public Message(String senderUID, String targetUID) {
        this.senderUID = senderUID;
        this.targetUID = targetUID;
    }

    public String getSenderUID() {
        return senderUID;
    }

    public void setSenderUID(String senderUID) {
        this.senderUID = senderUID;
    }

    public String getTargetUID() {
        return targetUID;
    }

    public void setTargetUID(String targetUID) {
        this.targetUID = targetUID;
    }
}
