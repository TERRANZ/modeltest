package ru.terra.modeltest.core.handler.impl;

import ru.terra.modeltest.core.WorldExecutor;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.impl.MaleAgent;
import ru.terra.modeltest.core.handler.MessageHandler;
import ru.terra.modeltest.core.message.impl.FriendshipMessage;
import ru.terra.modeltest.core.message.impl.PutFriendMessage;

public class FriendshipMessageHandler implements MessageHandler<FriendshipMessage> {
    @Override
    public void apply(Agent agent, FriendshipMessage message) {
        if (!agent.getInfo().getPossibleFriends().containsKey(message.getSenderUID())
                ||
                agent.getInfo().getPossibleFriends().get(message.getSenderUID())) {

            //message to sender: put us to friends
            WorldExecutor.getInstance().getAgentsWorld().postMessageToBoard(
                    new PutFriendMessage(agent.getUid(), message.getSenderUID(), agent instanceof MaleAgent)
            );

            //mesasge to us: put sender to our friends
            WorldExecutor.getInstance().getAgentsWorld().postMessageToBoard(
                    new PutFriendMessage(message.getSenderUID(), agent.getUid(), message.getMale())
            );
        }
    }
}
