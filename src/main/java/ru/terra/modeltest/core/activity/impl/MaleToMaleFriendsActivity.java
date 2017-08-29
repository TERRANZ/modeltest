package ru.terra.modeltest.core.activity.impl;

import ru.terra.modeltest.core.activity.Activity;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.impl.MaleAgent;
import ru.terra.modeltest.core.message.Message;
import ru.terra.modeltest.core.message.impl.FriendshipAcceptedMessage;
import ru.terra.modeltest.core.message.impl.WantFriendsMessage;

public class MaleToMaleFriendsActivity implements Activity<WantFriendsMessage> {
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

    @Override
    public boolean applicable(Message m) {
        return m instanceof WantFriendsMessage;
    }
}
