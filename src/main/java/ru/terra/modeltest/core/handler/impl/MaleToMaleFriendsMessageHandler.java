package ru.terra.modeltest.core.handler.impl;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.impl.MaleAgent;
import ru.terra.modeltest.core.handler.MessageHandler;
import ru.terra.modeltest.core.message.impl.FriendshipAcceptedMessage;
import ru.terra.modeltest.core.message.impl.FriendshipMessage;

public class MaleToMaleFriendsMessageHandler implements MessageHandler<FriendshipMessage> {
    @Override
    public void apply(Agent agent, FriendshipMessage message) {
        if (message.getType().equals(MaleAgent.class)) {
            if (!agent.getInfo().getFriends().containsKey(message.getSenderUID())) {
                agent.getInfo().getFriends().put(message.getSenderUID(), true);
                agent.getWorld().postMessageToBoard(new FriendshipAcceptedMessage(
                        agent.getUid(),
                        message.getSenderUID(),
                        agent instanceof MaleAgent
                ));
            }
        }
    }
}
