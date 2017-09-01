package ru.terra.modeltest.core.message.impl;

import ru.terra.modeltest.core.AgentsWorld;
import ru.terra.modeltest.core.message.Message;

public class AskArbiterMessage extends Message {
    public AskArbiterMessage(String senderUID) {
        super(senderUID, AgentsWorld.ARBITER_UID);
    }
}
