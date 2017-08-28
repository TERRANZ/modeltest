package ru.terra.modeltest.core.activity.impl;

import ru.terra.modeltest.core.activity.Activity;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.AgentState;

public class ActivityInit extends Activity {
    @Override
    public void apply(Agent agent) {
        agent.changeState(AgentState.STARTED);
    }
}
