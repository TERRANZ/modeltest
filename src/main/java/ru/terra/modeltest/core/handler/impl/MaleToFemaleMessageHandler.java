package ru.terra.modeltest.core.handler.impl;

import org.apache.log4j.Logger;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.impl.MaleAgent;
import ru.terra.modeltest.core.handler.MessageHandler;
import ru.terra.modeltest.core.message.impl.FriendshipAcceptedMessage;
import ru.terra.modeltest.core.message.impl.WantFriendsMessage;

public class MaleToFemaleMessageHandler implements MessageHandler<WantFriendsMessage> {
    protected Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void apply(Agent agent, WantFriendsMessage message) {
        if ((message).getType().equals(MaleAgent.class)) {
            logger.info("Adding male " + message.getSenderUID() + " to friend");
            agent.getInfo().getFriends().put(message.getSenderUID(), true);
            agent.getWorld().postMessageToBoard(new FriendshipAcceptedMessage(
                    agent.getInfo().getUid(),
                    message.getSenderUID(),
                    agent instanceof MaleAgent
            ));
            agent.getInfo().getFriends().forEach((uid, m) ->
                    agent.getWorld().postMessageToBoard(new FriendshipAcceptedMessage(
                            uid,
                            message.getSenderUID(),
                            m
                    )));
        }
    }
}
