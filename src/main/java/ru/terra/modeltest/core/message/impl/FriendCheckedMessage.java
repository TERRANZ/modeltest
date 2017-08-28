package ru.terra.modeltest.core.message.impl;

import ru.terra.modeltest.core.message.Message;

public class FriendCheckedMessage extends Message {
    private Boolean result;
    private String checkingUID;

    public FriendCheckedMessage(String senderUID, String targetUID, Boolean result, String checkingUID) {
        super(senderUID, targetUID);
        this.result = result;
        this.checkingUID = checkingUID;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getCheckingUID() {
        return checkingUID;
    }

    public void setCheckingUID(String checkingUID) {
        this.checkingUID = checkingUID;
    }
}
