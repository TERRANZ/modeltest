package ru.terra.modeltest.core;

import org.apache.log4j.Logger;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.AgentInfo;
import ru.terra.modeltest.core.agent.impl.ArbiterAgent;
import ru.terra.modeltest.core.message.Message;
import ru.terra.modeltest.storage.Storage;

import java.util.Map;

public class AgentsWorld {
    public static final String ARBITER_UID = "0";
    private Logger logger = Logger.getLogger(this.getClass());
    private Storage storage;
    private Board board;

    public AgentsWorld() {
        board = new Board(this);
        addArbiter();
    }

    public void addAgent(Agent agent) {
        synchronized (board.getAgentMap()) {
            logger.info("Adding agent " + agent.getInfo().getName() + " of class " + agent.getClass());
            agent.setWorld(this);
            board.addAgent(agent);
        }
    }

    public void postMessageToBoard(Message message) {
        board.postMessage(message);
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void loadState() {
    }

    public void saveState() {
    }

    public synchronized Map<String, Agent> getAgents() {
        return board.getAgentMap();
    }

    private void addArbiter() {
        Agent arbiter = new ArbiterAgent();
        AgentInfo ai = new AgentInfo();
        ai.setUid(ARBITER_UID);
        ai.setName("Arbiter");
        arbiter.setInfo(ai);
        addAgent(arbiter);
    }
}