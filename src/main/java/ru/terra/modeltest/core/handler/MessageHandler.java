package ru.terra.modeltest.core.handler;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.message.Message;

public interface MessageHandler<T extends Message> {
    void apply(Agent agent, T message);
}
