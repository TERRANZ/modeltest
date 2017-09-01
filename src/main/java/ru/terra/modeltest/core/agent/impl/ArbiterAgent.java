package ru.terra.modeltest.core.agent.impl;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.handler.impl.AskArbiterMessageHandler;
import ru.terra.modeltest.core.message.impl.AskArbiterMessage;

public class ArbiterAgent extends Agent {
    public ArbiterAgent() {
        addHandler(AskArbiterMessage.class, new AskArbiterMessageHandler());
    }
}
