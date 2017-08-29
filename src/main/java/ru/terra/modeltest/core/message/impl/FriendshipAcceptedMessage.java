package ru.terra.modeltest.core.message.impl;

import ru.terra.modeltest.core.message.Message;

public class FriendshipAcceptedMessage extends Message {
    private Boolean male;

    public FriendshipAcceptedMessage(String senderUID, String targetUID, Boolean male) {
        super(senderUID, targetUID);
        this.male = male;
    }

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }
}
