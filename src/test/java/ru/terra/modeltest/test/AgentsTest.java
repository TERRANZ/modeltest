package ru.terra.modeltest.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import ru.terra.modeltest.core.AgentsWorld;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.AgentInfo;
import ru.terra.modeltest.core.agent.impl.FemaleAgent;
import ru.terra.modeltest.core.agent.impl.MaleAgent;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AgentsTest {
    private Logger logger = Logger.getLogger(this.getClass());

    @Test
    public void startTest() {
        AgentsWorld agentsWorld = new AgentsWorld();
        logger.info("Started");
    }

    @Test
    public void addAgentsTest() throws InterruptedException {
        ExecutorService tp = Executors.newCachedThreadPool();
        tp.submit(() -> {
            final AgentsWorld agentsWorld = new AgentsWorld();
            agentsWorld.addAgent(generateAgent(true, "Артём"));
            agentsWorld.addAgent(generateAgent(true, "Иван"));
            agentsWorld.addAgent(generateAgent(false, "Маша"));
            agentsWorld.addAgent(generateAgent(false, "Кристина"));
        });
        tp.awaitTermination(1, TimeUnit.HOURS);
    }

    private Agent generateAgent(Boolean male, String name) {
        AgentInfo ai = new AgentInfo();
        ai.setName(name);
        ai.setUid(UUID.randomUUID().toString());
        ai.setFriends(new HashMap<>());
        Agent agent;
        if (male)
            agent = new MaleAgent();
        else
            agent = new FemaleAgent();
        agent.setInfo(ai);
        return agent;
    }
}
