package ru.terra.modeltest.core.activity;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.message.Message;

public interface Activity {
    void apply(Agent agent, Message message);
}
