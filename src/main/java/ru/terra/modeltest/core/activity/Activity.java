package ru.terra.modeltest.core.activity;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.message.Message;

public interface Activity<T extends Message> {
    void apply(Agent agent, T message);

    boolean applicable(Message m);
}
