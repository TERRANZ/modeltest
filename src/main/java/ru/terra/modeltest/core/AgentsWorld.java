package ru.terra.modeltest.core;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.message.impl.WantFriendsMessage;
import ru.terra.modeltest.storage.Storage;

import java.util.List;

public class AgentsWorld {
    private List<Agent> agents;
    private Storage storage;
    private Board board;

    public AgentsWorld() {
        //0: loading
        storage = new Storage();
        agents = storage.loadAgents();
        board = new Board();
        //1: subscribing on board
        agents.parallelStream().forEach(board::addAgent);
    }

    public void addAgent(Agent agent) {
        agents.add(agent);
        storage.persistAgents(agents);
        board.addAgent(agent);
        board.sendBroadcast(new WantFriendsMessage(agent.getContext().getUid(), agent.getClass()));
    }
}