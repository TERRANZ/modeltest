package ru.terra.modeltest.core.handler.impl;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.handler.MessageHandler;
import ru.terra.modeltest.core.message.impl.FriendshipAcceptedMessage;

public class FriendshipAcceptedMessageHandler implements MessageHandler<FriendshipAcceptedMessage> {
    @Override
    public void apply(Agent agent, FriendshipAcceptedMessage message) {
        if (!message.getSenderUID().equals(agent.getUid()))
            agent.getInfo().getFriends().put(message.getSenderUID(), message.getMale());
    }
}
