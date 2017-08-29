package ru.terra.modeltest.core.activity.impl;

import ru.terra.modeltest.core.activity.Activity;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.message.Message;
import ru.terra.modeltest.core.message.impl.ProactiveMessage;

public class ProactiveActivity implements Activity<ProactiveMessage> {
    @Override
    public void apply(Agent agent, ProactiveMessage message) {

    }

    @Override
    public boolean applicable(Message m) {
        return m instanceof ProactiveMessage;
    }
}
