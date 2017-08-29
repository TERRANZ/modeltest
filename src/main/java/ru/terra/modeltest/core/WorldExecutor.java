package ru.terra.modeltest.core;

import java.util.concurrent.Executors;

public class WorldExecutor {
    private static WorldExecutor instance = new WorldExecutor();
    private AgentsWorld agentsWorld;

    private WorldExecutor() {
        Executors.newFixedThreadPool(1).submit(() -> agentsWorld = new AgentsWorld());
    }

    public static WorldExecutor getInstance() {
        return instance;
    }

    public AgentsWorld getAgentsWorld() {
        return agentsWorld;
    }
}
