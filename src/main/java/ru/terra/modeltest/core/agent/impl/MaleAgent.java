package ru.terra.modeltest.core.agent.impl;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.message.Message;
import ru.terra.modeltest.core.message.impl.WantFriendsMessage;

public class MaleAgent extends Agent {
    @Override
    public void processMessage(Message m) {
        logger.info("Processing message " + m.getClass());
        if (m instanceof WantFriendsMessage) {
            WantFriendsMessage message = ((WantFriendsMessage) m);
            logger.info("Message from " + message.getSenderUID() + " and its " + message.getType());
            //somebody wants to be friends with this
            if (message.getType().equals(MaleAgent.class)) {
                //male want to be friend
                if (!getContext().getFriends().contains(m.getSenderUID())) {
                    getContext().getFriends().add(m.getSenderUID());
                } else {
                    logger.info(m.getSenderUID() + " already in friends list");
                }
            } else {
            }
        }
    }
}
