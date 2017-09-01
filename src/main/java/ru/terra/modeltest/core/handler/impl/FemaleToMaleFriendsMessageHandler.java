package ru.terra.modeltest.core.handler.impl;

import org.apache.log4j.Logger;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.impl.FemaleAgent;
import ru.terra.modeltest.core.agent.impl.MaleAgent;
import ru.terra.modeltest.core.handler.MessageHandler;
import ru.terra.modeltest.core.message.Message;
import ru.terra.modeltest.core.message.impl.CheckFriendMessage;
import ru.terra.modeltest.core.message.impl.FriendCheckedMessage;
import ru.terra.modeltest.core.message.impl.FriendshipAcceptedMessage;
import ru.terra.modeltest.core.message.impl.FriendshipMessage;

import java.util.HashMap;
import java.util.Map;

public class FemaleToMaleFriendsMessageHandler implements MessageHandler {
    private Map<String, Boolean> friendsToCheck = new HashMap<>();
    private Integer friendsToCheckCount = 0;
    protected Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void apply(Agent agent, Message message) {
        if (message instanceof FriendshipMessage && ((FriendshipMessage) message).getType().equals(FemaleAgent.class)) {
            if (!agent.getInfo().getFriends().containsKey(message.getSenderUID())) {
                //check if other male friends have this female in friends
                //if we have no friends just add
                if (agent.getInfo().getFriends().isEmpty()) {
                    agent.getInfo().getFriends().put(message.getSenderUID(), false);
                    agent.getWorld().postMessageToBoard(new FriendshipAcceptedMessage(
                            agent.getUid(),
                            message.getSenderUID(),
                            agent instanceof MaleAgent
                    ));
                } else
                    agent.getInfo().getFriends().forEach((uid, male) -> {
                        if (male) {
                            agent.getWorld().postMessageToBoard(new CheckFriendMessage(agent.getUid(), uid, message.getSenderUID()));
                            logger.info("Asking " + uid + " to check is female friend in their list");
                            friendsToCheck.put(uid, false);
                            friendsToCheckCount++;
                        }
                    });
            }
        } else if (message instanceof FriendCheckedMessage) {
            FriendCheckedMessage fcm = ((FriendCheckedMessage) message);
            logger.info("FriendCheckedMessage from " + message.getSenderUID() + " result: " + fcm.getResult());
            friendsToCheck.put(message.getSenderUID(), fcm.getResult());
            friendsToCheckCount--;
            if (friendsToCheckCount == 0) {
                logger.info("All friends are checked and all have " + fcm.getCheckingUID() + " in friends");
                agent.getInfo().getFriends().put(fcm.getCheckingUID(), false);
                agent.getWorld().postMessageToBoard(new FriendshipAcceptedMessage(
                        agent.getUid(),
                        message.getSenderUID(),
                        agent instanceof MaleAgent
                ));
            }
        }
    }
}
