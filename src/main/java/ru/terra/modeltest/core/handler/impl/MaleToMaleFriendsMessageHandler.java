package ru.terra.modeltest.core.handler.impl;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.impl.MaleAgent;
import ru.terra.modeltest.core.handler.MessageHandler;
import ru.terra.modeltest.core.message.impl.FriendshipAcceptedMessage;
import ru.terra.modeltest.core.message.impl.WantFriendsMessage;

public class MaleToMaleFriendsMessageHandler implements MessageHandler<WantFriendsMessage> {
    @Override
    public void apply(Agent agent, WantFriendsMessage message) {
        if (message.getType().equals(MaleAgent.class)) {
            if (!agent.getInfo().getFriends().containsKey(message.getSenderUID())) {
                agent.getInfo().getFriends().put(message.getSenderUID(), true);
                agent.getWorld().postMessageToBoard(new FriendshipAcceptedMessage(
                        agent.getInfo().getUid(),
                        message.getSenderUID(),
                        agent instanceof MaleAgent
                ));
            }
        }
    }
}
