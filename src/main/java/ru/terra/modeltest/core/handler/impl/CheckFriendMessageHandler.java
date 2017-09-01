package ru.terra.modeltest.core.handler.impl;


import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.handler.MessageHandler;
import ru.terra.modeltest.core.message.impl.CheckFriendMessage;
import ru.terra.modeltest.core.message.impl.FriendCheckedMessage;

public class CheckFriendMessageHandler implements MessageHandler<CheckFriendMessage> {
    @Override
    public void apply(Agent agent, CheckFriendMessage message) {
        agent.getWorld().postMessageToBoard(
                new FriendCheckedMessage(
                        agent.getUid(),
                        message.getSenderUID(),
                        agent.getInfo().getFriends().containsKey(message.getCheckingUID()),
                        message.getCheckingUID()
                )
        );

    }
}
