package ru.terra.modeltest.core.message.impl;

import ru.terra.modeltest.core.message.Message;

//want to be mutual friends
public class FriendshipMessage extends Message {
    private Boolean male;

    public FriendshipMessage(String senderUID, String targetUID, Boolean male) {
        super(senderUID, targetUID);
        this.male = male;
    }

    public Boolean getMale() {
        return male;
    }
}
