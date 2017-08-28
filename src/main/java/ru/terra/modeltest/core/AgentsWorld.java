package ru.terra.modeltest.core;

import ru.terra.modeltest.core.activity.impl.ActivityInit;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.storage.Storage;

import java.util.List;

public class AgentsWorld {
    private List<Agent> agents;
    private Storage storage;

    public AgentsWorld() {
        storage = new Storage();
        agents = storage.loadAgents();
        ActivityInit init = new ActivityInit();
        agents.parallelStream().forEach(init::apply);
    }
}