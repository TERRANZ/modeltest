package ru.terra.modeltest.core;

import org.apache.log4j.Logger;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.AgentInfo;
import ru.terra.modeltest.core.agent.impl.ArbiterAgent;
import ru.terra.modeltest.core.message.Message;
import ru.terra.modeltest.storage.Storage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AgentsWorld {
    public static final String ARBITER_UID = "0";
    private Logger logger = Logger.getLogger(this.getClass());
    private Storage storage;
    private Board board;
    private Map<String, Agent> agentMap;

    public AgentsWorld() {
        agentMap = new ConcurrentHashMap<>();
        board = new Board(this);
        addArbiter();
    }

    public void addAgent(Agent agent) {
        synchronized (agentMap) {
            logger.info("Adding agent " + agent.getInfo().getName() + " of class " + agent.getClass());
            agentMap.put(agent.getUid(), agent);
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
        return agentMap;
    }

    private void addArbiter() {
        Agent arbiter = new ArbiterAgent();
        AgentInfo ai = new AgentInfo();
        arbiter.setUid(ARBITER_UID);
        ai.setName("Arbiter");
        arbiter.setInfo(ai);
        addAgent(arbiter);
    }
}