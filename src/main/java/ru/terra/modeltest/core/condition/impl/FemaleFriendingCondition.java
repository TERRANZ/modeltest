package ru.terra.modeltest.core.condition.impl;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.condition.Condition;
import ru.terra.modeltest.core.message.Message;
import ru.terra.modeltest.core.message.impl.FriendshipMessage;

//females only friend males
public class FemaleFriendingCondition extends Condition {
    @Override
    public boolean check(Agent agent, Message message) {
        if (message instanceof FriendshipMessage) {
            return ((FriendshipMessage) message).getMale();
        }
        return true;
    }
}
