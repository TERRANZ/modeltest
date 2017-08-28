package ru.terra.modeltest.core.message.impl;

import ru.terra.modeltest.core.message.Message;

public class CheckFriendMessage extends Message {

    private String checkingUID;

    public CheckFriendMessage(String senderUID, String targetUID, String checkingUID) {
        super(senderUID, targetUID);
        this.checkingUID = checkingUID;
    }

    public String getCheckingUID() {
        return checkingUID;
    }

    public void setCheckingUID(String checkingUID) {
        this.checkingUID = checkingUID;
    }
}
