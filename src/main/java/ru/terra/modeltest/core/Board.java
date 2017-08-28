package ru.terra.modeltest.core;

import org.apache.log4j.Logger;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.message.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Board {
    private Logger logger = Logger.getLogger(this.getClass());
    private Map<String, Agent> agentMap;
    BlockingQueue<Message> queue = new LinkedBlockingQueue<>(10000);
    private AgentsWorld world;

    public Board(AgentsWorld world) {
        this.world = world;
        agentMap = new ConcurrentHashMap<>();
        Executors.newFixedThreadPool(1).submit(() -> {
            while (true) {
                try {
                    tick();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public synchronized void addAgent(Agent agent) {
        agentMap.put(agent.getInfo().getUid(), agent);
    }


    public void postMessage(Message message) {
        logger.info("Placing message " + message.getClass());
        queue.add(message);
    }

    private void tick() {
        List<Message> messages = new ArrayList<>();
        queue.drainTo(messages);
        logger.info("Dispatching " + messages.size() + " messages");
        if (!messages.isEmpty()) {
            messages.parallelStream().forEach(m -> {
                if (m.getTargetUID() != null) {
                    if (agentMap.containsKey(m.getTargetUID())) {
                        agentMap.get(m.getTargetUID()).processMessage(m);
                    }
                } else {
                    agentMap.values().forEach(agent -> agent.processMessage(m));
                }
            });
        }
        world.saveState();
    }

}
