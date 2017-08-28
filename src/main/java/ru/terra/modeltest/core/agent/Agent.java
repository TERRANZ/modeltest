package ru.terra.modeltest.core.agent;

import ru.terra.modeltest.core.message.Message;

import java.util.List;

public abstract class Agent {
    private AgentInfo context;
    private List<Condition> conditions;
    private List<Capability> capabilities;
    private AgentState state = AgentState.INIT;

    public AgentInfo getContext() {
        return context;
    }

    public void setContext(AgentInfo context) {
        this.context = context;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public List<Capability> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<Capability> capabilities) {
        this.capabilities = capabilities;
    }

    public void changeState(AgentState newState) {
        state = newState;
    }

    public AgentState getState() {
        return state;
    }

    public void processMessage(Message m) {

    }
}
