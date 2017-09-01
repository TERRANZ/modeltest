package ru.terra.modeltest.core.agent.impl;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.handler.impl.*;
import ru.terra.modeltest.core.message.impl.*;

public class MaleAgent extends Agent {

    public MaleAgent() {
        addHandler(FriendshipMessage.class, new MaleToMaleFriendsMessageHandler());
        addHandler(FriendshipMessage.class, new FemaleToMaleFriendsMessageHandler());
        addHandler(FriendCheckedMessage.class, new FemaleToMaleFriendsMessageHandler());
        addHandler(CheckFriendMessage.class, new CheckFriendMessageHandler());
        addHandler(FriendshipAcceptedMessage.class, new FriendshipAcceptedMessageHandler());
        addHandler(FemaleFriendsAcceptMessage.class, new FemaleFriendsMessageHandler());
    }

}
