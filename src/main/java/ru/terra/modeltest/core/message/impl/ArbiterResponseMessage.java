package ru.terra.modeltest.core.message.impl;

import ru.terra.modeltest.core.message.Message;

public class ArbiterResponseMessage extends Message {
    private Boolean result;

    public ArbiterResponseMessage(String senderUID, String targetUID, Boolean result) {
        super(senderUID, targetUID);
        this.result = result;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
