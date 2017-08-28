package ru.terra.modeltest.core;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.message.Message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Board {

    private Map<String, Agent> agentMap;

    public Board() {
        agentMap = new ConcurrentHashMap<>();
    }

    public synchronized void addAgent(Agent agent) {
        agentMap.put(agent.getContext().getUid(), agent);
    }

    public void sendDirectMessage(String uid, Message message) {
        if (agentMap.containsKey(uid))
            agentMap.get(uid).processMessage(message);
    }

    public void sendBroadcast(Message message) {

    }

    public void tick() {
        synchronized (agentMap) {

        }
    }
}
