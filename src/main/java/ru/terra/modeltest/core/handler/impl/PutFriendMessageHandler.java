package ru.terra.modeltest.core.handler.impl;

import ru.terra.modeltest.core.WorldExecutor;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.handler.MessageHandler;
import ru.terra.modeltest.core.message.impl.AskArbiterMessage;
import ru.terra.modeltest.core.message.impl.PutFriendMessage;

public class PutFriendMessageHandler implements MessageHandler<PutFriendMessage> {
    @Override
    public void apply(Agent agent, PutFriendMessage message) {
        if (!agent.getUid().equals(message.getSenderUID())) {
            agent.getInfo().getFriends().put(message.getSenderUID(), message.getMale());
            WorldExecutor.getInstance().getAgentsWorld().postMessageToBoard(new AskArbiterMessage(agent.getUid()));
        }
    }
}
