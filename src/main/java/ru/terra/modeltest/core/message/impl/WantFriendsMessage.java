package ru.terra.modeltest.core.message.impl;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.message.Message;

//want to be mutual friends
public class WantFriendsMessage extends Message {
    private Class<? extends Agent> type;

    public WantFriendsMessage(String senderUID, String targetUID, Class<? extends Agent> type) {
        super(senderUID, targetUID);
        this.type = type;
    }

    public WantFriendsMessage(String senderUID, Class<? extends Agent> type) {
        super(senderUID);
        this.type = type;
    }

    public Class<? extends Agent> getType() {
        return type;
    }
}
