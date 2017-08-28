package ru.terra.modeltest.core;

import org.apache.log4j.Logger;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.AgentState;
import ru.terra.modeltest.core.message.Message;
import ru.terra.modeltest.core.message.impl.WantFriendsMessage;
import ru.terra.modeltest.storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class AgentsWorld {
    private Logger logger = Logger.getLogger(this.getClass());
    private List<Agent> agents = new ArrayList<>();
    private Storage storage;
    private Board board;

    public AgentsWorld() {
        //0: loading
        storage = new Storage();
//        agents = storage.loadAgents();
        logger.info("Loaded " + agents.size() + " agents");
        board = new Board(this);
        //1: subscribing on board
        agents.parallelStream().forEach(a -> {
                    a.setWorld(this);
                    board.addAgent(a);
                    a.changeState(AgentState.DONE);
                }
        );
    }

    public void addAgent(Agent agent) {
        synchronized (agents) {
            logger.info("Adding agent " + agent.getInfo().getName() + " of class " + agent.getClass());
            agents.add(agent);
            board.addAgent(agent);
            agent.setWorld(this);
            agent.changeState(AgentState.DONE);
            board.postMessage(new WantFriendsMessage(agent.getInfo().getUid(), agent.getClass()));
        }
    }

    public void postMessageToBoard(Message message) {
        board.postMessage(message);
    }

    public void saveState() {
        storage.persistAgents(agents);
    }
}