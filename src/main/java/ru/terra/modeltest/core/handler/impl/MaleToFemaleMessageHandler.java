package ru.terra.modeltest.core.handler.impl;

import org.apache.log4j.Logger;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.impl.MaleAgent;
import ru.terra.modeltest.core.handler.MessageHandler;
import ru.terra.modeltest.core.message.impl.FemaleFriendsAcceptMessage;
import ru.terra.modeltest.core.message.impl.FriendshipAcceptedMessage;
import ru.terra.modeltest.core.message.impl.FriendshipMessage;

public class MaleToFemaleMessageHandler implements MessageHandler<FriendshipMessage> {
    protected Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void apply(Agent agent, FriendshipMessage message) {
        if ((message).getType().equals(MaleAgent.class)) {
            logger.info("Adding male " + message.getSenderUID() + " to friend");
            agent.getInfo().getFriends().put(message.getSenderUID(), true);
            agent.getWorld().postMessageToBoard(new FriendshipAcceptedMessage(
                    agent.getUid(),
                    message.getSenderUID(),
                    agent instanceof MaleAgent
            ));
            agent.getWorld().postMessageToBoard(new FemaleFriendsAcceptMessage(
                    agent.getUid(),
                    message.getSenderUID(),
                    agent.getInfo().getFriends().keySet()
            ));
        }
    }
}
