package ru.terra.modeltest.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import ru.terra.modeltest.core.AgentsWorld;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.AgentInfo;
import ru.terra.modeltest.core.agent.impl.FemaleAgent;
import ru.terra.modeltest.core.agent.impl.MaleAgent;

import java.util.Date;
import java.util.UUID;

public class AgentsTest {
    private Logger logger = Logger.getLogger(this.getClass());

    @Test
    public void startTest() {
        AgentsWorld agentsWorld = new AgentsWorld();
        logger.info("Started");
    }

    @Test
    public void addAgentsTest() {
        AgentsWorld agentsWorld = new AgentsWorld();
        for (int i = 0; i < 50; i++) {
            AgentInfo ai = new AgentInfo();
            ai.setName("Agent " + i);
            ai.setUid(UUID.randomUUID().toString());
            if ((new Date().getTime() & 1) == 0) {
                Agent fa = new FemaleAgent();
                fa.setContext(ai);
                agentsWorld.addAgent(fa);
            } else {
                Agent ma = new MaleAgent();
                ma.setContext(ai);
                agentsWorld.addAgent(ma);
            }
        }
    }
}
