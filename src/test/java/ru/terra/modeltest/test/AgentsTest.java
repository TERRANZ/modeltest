package ru.terra.modeltest.test;

import org.apache.log4j.Logger;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.AgentInfo;
import ru.terra.modeltest.core.agent.impl.FemaleAgent;
import ru.terra.modeltest.core.agent.impl.MaleAgent;

import java.util.HashMap;
import java.util.UUID;

public class AgentsTest {
    private Logger logger = Logger.getLogger(this.getClass());

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
