package ru.terra.modeltest.core.handler.impl;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.handler.MessageHandler;
import ru.terra.modeltest.core.message.impl.FemaleFriendsAcceptMessage;

public class FemaleFriendsMessageHandler implements MessageHandler<FemaleFriendsAcceptMessage> {
    @Override
    public void apply(Agent agent, FemaleFriendsAcceptMessage message) {
        String myUID = agent.getInfo().getUid();
        if (!message.getSenderUID().equals(myUID)) {
            message.getUids().stream().filter(uid -> !uid.equals(myUID)).forEach(uid -> agent.getInfo().getFriends().put(uid, true));
        }
    }
}
