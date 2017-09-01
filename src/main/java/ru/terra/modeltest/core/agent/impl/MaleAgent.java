package ru.terra.modeltest.core.agent.impl;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.handler.impl.FemaleFriendsMessageHandler;
import ru.terra.modeltest.core.handler.impl.PutFriendMessageHandler;
import ru.terra.modeltest.core.handler.impl.FriendshipMessageHandler;
import ru.terra.modeltest.core.message.impl.FemaleFriendsAcceptMessage;
import ru.terra.modeltest.core.message.impl.PutFriendMessage;
import ru.terra.modeltest.core.message.impl.FriendshipMessage;

public class MaleAgent extends Agent {

    public MaleAgent() {
        addHandler(FriendshipMessage.class, new FriendshipMessageHandler());
        addHandler(PutFriendMessage.class, new PutFriendMessageHandler());
        addHandler(FemaleFriendsAcceptMessage.class, new FemaleFriendsMessageHandler());
    }

}
