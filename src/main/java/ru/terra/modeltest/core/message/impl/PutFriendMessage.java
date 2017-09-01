package ru.terra.modeltest.core.message.impl;

import ru.terra.modeltest.core.message.Message;

public class PutFriendMessage extends Message {
    private Boolean male;

    public PutFriendMessage(String senderUID, String targetUID, Boolean male) {
        super(senderUID, targetUID);
        this.male = male;
    }

    public Boolean getMale() {
        return male;
    }
}
