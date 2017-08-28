package ru.terra.modeltest.core.activity.impl;

import ru.terra.modeltest.core.activity.Activity;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.impl.MaleAgent;
import ru.terra.modeltest.core.message.Message;
import ru.terra.modeltest.core.message.impl.WantFriendsMessage;

public class MaleToMaleFriendsActivity implements Activity {
    @Override
    public void apply(Agent agent, Message message) {
        if (message instanceof WantFriendsMessage && ((WantFriendsMessage) message).getType().equals(MaleAgent.class)) {
            if (!agent.getInfo().getFriends().containsKey(message.getSenderUID()))
                agent.getInfo().getFriends().put(message.getSenderUID(), true);
        }
    }
}
