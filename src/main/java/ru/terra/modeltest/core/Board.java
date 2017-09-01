package ru.terra.modeltest.core;

import org.apache.log4j.Logger;
import ru.terra.modeltest.core.message.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Board {
    private Logger logger = Logger.getLogger(this.getClass());
    BlockingQueue<Message> queue = new LinkedBlockingQueue<>(10000);
    private AgentsWorld world;

    public Board(AgentsWorld world) {
        this.world = world;
    }

    public void postMessage(Message m) {
        if (m.getTargetUID() != null) {
            if (world.getAgents().containsKey(m.getTargetUID())) {
                world.getAgents().get(m.getTargetUID()).processMessage(m);
            }
        } else {
            world.getAgents().values().stream().filter(agent -> !agent.getUid().equals(m.getSenderUID())).forEach(agent -> agent.processMessage(m));
        }
    }

    private void tick() {
        List<Message> messages = new ArrayList<>();
        queue.drainTo(messages);
//        logger.info("Dispatching " + messages.size() + " messages");
        if (!messages.isEmpty()) {
            messages.parallelStream().forEach(m -> {
                if (m.getTargetUID() != null) {
                    if (world.getAgents().containsKey(m.getTargetUID())) {
                        world.getAgents().get(m.getTargetUID()).processMessage(m);
                    }
                } else {
                    world.getAgents().values().stream().filter(agent -> !agent.getUid().equals(m.getSenderUID())).forEach(agent -> agent.processMessage(m));
                }
            });
        }
    }
}
