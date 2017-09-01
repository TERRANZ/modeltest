package ru.terra.modeltest.core.handler.impl;

import ru.terra.modeltest.core.WorldExecutor;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.handler.MessageHandler;
import ru.terra.modeltest.core.message.impl.FemaleFriendsAcceptMessage;
import ru.terra.modeltest.core.message.impl.FriendshipMessage;

//if male want to friend female - send females friends to male
public class MaleToFemaleFriendshipMessageHandler implements MessageHandler<FriendshipMessage> {
    @Override
    public void apply(Agent agent, FriendshipMessage message) {
        if (!agent.getInfo().getPossibleFriends().containsKey(message.getSenderUID())
                ||
                agent.getInfo().getPossibleFriends().get(message.getSenderUID())) {
            WorldExecutor.getInstance().getAgentsWorld().postMessageToBoard(
                    new FemaleFriendsAcceptMessage(
                            agent.getUid(),
                            message.getSenderUID(),
                            agent.getInfo().getFriends().keySet()
                    )
            );
        }
    }
}
