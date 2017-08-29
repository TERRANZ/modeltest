package ru.terra.modeltest.core.activity.impl;


import ru.terra.modeltest.core.activity.Activity;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.message.Message;
import ru.terra.modeltest.core.message.impl.CheckFriendMessage;
import ru.terra.modeltest.core.message.impl.FriendCheckedMessage;

public class CheckFriendActivity implements Activity<CheckFriendMessage> {
    @Override
    public void apply(Agent agent, CheckFriendMessage message) {
        agent.getWorld().postMessageToBoard(
                new FriendCheckedMessage(
                        agent.getInfo().getUid(),
                        message.getSenderUID(),
                        agent.getInfo().getFriends().containsKey(message.getCheckingUID()),
                        message.getCheckingUID()
                )
        );

    }

    @Override
    public boolean applicable(Message m) {
        return m instanceof CheckFriendMessage;
    }
}
