package ru.terra.modeltest.core.activity;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.message.Message;

public abstract class Activity {
    public abstract void apply(Agent agent, Message message);
}
