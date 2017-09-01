package ru.terra.modeltest.core.agent.impl;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.condition.impl.FemaleFriendingCondition;
import ru.terra.modeltest.core.handler.impl.FriendshipMessageHandler;
import ru.terra.modeltest.core.handler.impl.MaleToFemaleFriendshipMessageHandler;
import ru.terra.modeltest.core.handler.impl.PutFriendMessageHandler;
import ru.terra.modeltest.core.message.impl.FriendshipMessage;
import ru.terra.modeltest.core.message.impl.PutFriendMessage;

public class FemaleAgent extends Agent {
    public FemaleAgent() {
        getConditions().add(new FemaleFriendingCondition());

        addHandler(FriendshipMessage.class, new FriendshipMessageHandler());
        addHandler(FriendshipMessage.class, new MaleToFemaleFriendshipMessageHandler());
        addHandler(PutFriendMessage.class, new PutFriendMessageHandler());
    }
}
