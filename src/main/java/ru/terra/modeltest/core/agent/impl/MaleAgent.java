package ru.terra.modeltest.core.agent.impl;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.handler.impl.CheckFriendMessageHandler;
import ru.terra.modeltest.core.handler.impl.FemaleToMaleFriendsMessageHandler;
import ru.terra.modeltest.core.handler.impl.FriendshipAcceptedMessageHandler;
import ru.terra.modeltest.core.handler.impl.MaleToMaleFriendsMessageHandler;
import ru.terra.modeltest.core.message.impl.CheckFriendMessage;
import ru.terra.modeltest.core.message.impl.FriendCheckedMessage;
import ru.terra.modeltest.core.message.impl.FriendshipAcceptedMessage;
import ru.terra.modeltest.core.message.impl.FriendshipMessage;

public class MaleAgent extends Agent {

    public MaleAgent() {
        addHandler(FriendshipMessage.class, new MaleToMaleFriendsMessageHandler());
        addHandler(FriendshipMessage.class, new FemaleToMaleFriendsMessageHandler());
        addHandler(FriendCheckedMessage.class, new FemaleToMaleFriendsMessageHandler());
        addHandler(CheckFriendMessage.class, new CheckFriendMessageHandler());
        addHandler(FriendshipAcceptedMessage.class, new FriendshipAcceptedMessageHandler());
    }

}
