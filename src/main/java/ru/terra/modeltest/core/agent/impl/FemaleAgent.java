package ru.terra.modeltest.core.agent.impl;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.handler.impl.FriendshipAcceptedMessageHandler;
import ru.terra.modeltest.core.handler.impl.MaleToFemaleMessageHandler;
import ru.terra.modeltest.core.message.impl.FriendshipAcceptedMessage;
import ru.terra.modeltest.core.message.impl.WantFriendsMessage;

public class FemaleAgent extends Agent {
    public FemaleAgent() {
        addHandler(WantFriendsMessage.class, new MaleToFemaleMessageHandler());
        addHandler(FriendshipAcceptedMessage.class, new FriendshipAcceptedMessageHandler());
    }
}
