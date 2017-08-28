package ru.terra.modeltest.core.message.impl;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.message.Message;

//want to be friend with selected agent class, male of female
public class DirectFriendMessage extends Message {

    private Class<? extends Agent> targetAgentClass;

    public DirectFriendMessage(String senderUID, Class<? extends Agent> targetAgentClass) {
        super(senderUID);
        this.targetAgentClass = targetAgentClass;
    }

    public Class<? extends Agent> getTargetAgentClass() {
        return targetAgentClass;
    }
}
