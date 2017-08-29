package ru.terra.modeltest.core.activity.impl;

import ru.terra.modeltest.core.activity.Activity;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.message.Message;
import ru.terra.modeltest.core.message.impl.FriendshipAcceptedMessage;

public class FriendshipAcceptedActivitiy implements Activity<FriendshipAcceptedMessage> {
    @Override
    public void apply(Agent agent, FriendshipAcceptedMessage message) {
        agent.getInfo().getFriends().put(message.getSenderUID(), message.getMale());
    }

    @Override
    public boolean applicable(Message m) {
        return m instanceof FriendshipAcceptedMessage;
    }
}
