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

    public Board() {
        agentMap = new ConcurrentHashMap<>();
        Executors.newFixedThreadPool(1).submit(() -> {
            while (true) {
                tick();
                try {
                    Thread.sleep(1000);
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
        queue.add(message);
    }

    private void tick() {
        logger.info("Dispatching messages");
        List<Message> messages = new ArrayList<>();
        queue.drainTo(messages);
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
    }

}
