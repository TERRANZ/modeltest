package ru.terra.modeltest.core;

import org.apache.log4j.Logger;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.message.Message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Board {
    private Logger logger = Logger.getLogger(this.getClass());
    private Map<String, Agent> agentMap;

    public Board() {
        agentMap = new ConcurrentHashMap<>();
    }

    public synchronized void addAgent(Agent agent) {
        agentMap.put(agent.getContext().getUid(), agent);
    }

    public void sendDirectMessage(String uid, Message message) {
        logger.info("Sending direct message to " + uid + " : " + message.getClass().getName());
        if (agentMap.containsKey(uid))
            agentMap.get(uid).processMessage(message);
    }

    public void sendBroadcast(Message message) {
        logger.info("Sending broadcast : " + message.getClass().getName());
        agentMap.values().forEach(a -> a.processMessage(message));
    }
}
