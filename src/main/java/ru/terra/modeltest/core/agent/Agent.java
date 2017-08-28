package ru.terra.modeltest.core.agent;

import java.util.ArrayList;
import java.util.List;

public class Agent {
    private AgentInfo context;
    private List<Condition> conditions;
    private List<Capability> capabilities;
    private AgentState state = AgentState.INIT;

    public Agent() {
        context = new AgentInfo();
        conditions = new ArrayList<>();
        capabilities = new ArrayList<>();
    }

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
}
