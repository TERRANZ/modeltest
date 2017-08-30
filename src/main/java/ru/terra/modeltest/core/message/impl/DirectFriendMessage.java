package ru.terra.modeltest.core.message.impl;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.message.Message;

public class DirectFriendMessage extends Message {
    private Class<? extends Agent> type;

    public DirectFriendMessage(String senderUID, String targetUID, Class<? extends Agent> type) {
        super(senderUID, targetUID);
        this.type = type;
    }

    public Class<? extends Agent> getType() {
        return type;
    }

    public void setType(Class<? extends Agent> type) {
        this.type = type;
    }
}
