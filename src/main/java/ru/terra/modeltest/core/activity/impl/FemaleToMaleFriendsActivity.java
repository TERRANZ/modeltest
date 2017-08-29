package ru.terra.modeltest.core.activity.impl;

import org.apache.log4j.Logger;
import ru.terra.modeltest.core.activity.Activity;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.impl.FemaleAgent;
import ru.terra.modeltest.core.message.Message;
import ru.terra.modeltest.core.message.impl.CheckFriendMessage;
import ru.terra.modeltest.core.message.impl.FriendCheckedMessage;
import ru.terra.modeltest.core.message.impl.WantFriendsMessage;

import java.util.HashMap;
import java.util.Map;

public class FemaleToMaleFriendsActivity implements Activity {
    private Map<String, Boolean> friendsToCheck = new HashMap<>();
    private Integer friendsToCheckCount = 0;
    protected Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void apply(Agent agent, Message message) {
        if (message instanceof WantFriendsMessage && ((WantFriendsMessage) message).getType().equals(FemaleAgent.class)) {
            if (!agent.getInfo().getFriends().containsKey(message.getSenderUID())) {
                //check if other male friends have this female in friends
                agent.getInfo().getFriends().forEach((uid, male) -> {
                    if (male) {
                        agent.getWorld().postMessageToBoard(new CheckFriendMessage(agent.getInfo().getUid(), uid, message.getSenderUID()));
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
            }
        }
    }

    @Override
    public boolean applicable(Message m) {
        return ((m instanceof WantFriendsMessage) || (m instanceof FriendCheckedMessage));
    }
}
