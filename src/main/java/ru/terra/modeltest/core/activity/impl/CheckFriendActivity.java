package ru.terra.modeltest.core.activity.impl;


import ru.terra.modeltest.core.activity.Activity;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.message.Message;
import ru.terra.modeltest.core.message.impl.CheckFriendMessage;
import ru.terra.modeltest.core.message.impl.FriendCheckedMessage;

public class CheckFriendActivity implements Activity {
    @Override
    public void apply(Agent agent, Message m) {
        if (m instanceof CheckFriendMessage) {
            CheckFriendMessage message = ((CheckFriendMessage) m);
            agent.getWorld().postMessageToBoard(
                    new FriendCheckedMessage(
                            agent.getInfo().getUid(),
                            m.getSenderUID(),
                            agent.getInfo().getFriends().containsKey(message.getCheckingUID()),
                            message.getCheckingUID()
                    )
            );
        }
    }
}
