package ru.terra.modeltest.core.condition;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.message.Message;

public abstract class Condition {
    public abstract boolean check(Agent agent, Message message);
}
