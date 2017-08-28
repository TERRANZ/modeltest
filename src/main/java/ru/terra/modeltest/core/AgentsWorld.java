package ru.terra.modeltest.core;

import org.apache.log4j.Logger;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.AgentState;
import ru.terra.modeltest.core.message.impl.WantFriendsMessage;
import ru.terra.modeltest.storage.Storage;

import java.util.List;

public class AgentsWorld {
    private Logger logger = Logger.getLogger(this.getClass());
    private List<Agent> agents;
    private Storage storage;
    private Board board;


    public AgentsWorld() {
        //0: loading
        storage = new Storage();
        agents = storage.loadAgents();
        logger.info("Loaded " + agents.size() + " agents");
        board = new Board();
        //1: subscribing on board
        agents.parallelStream().forEach(a -> {
                    board.addAgent(a);
                    a.changeState(AgentState.DONE);
                }
        );
//        while (true){
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void addAgent(Agent agent) {
        synchronized (agents) {
            logger.info("Adding agent " + agent.getInfo().getName());
            agents.add(agent);
            storage.persistAgents(agents);
            board.addAgent(agent);
            board.postMessage(new WantFriendsMessage(agent.getInfo().getUid(), agent.getClass()));
        }
    }
}