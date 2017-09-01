package ru.terra.modeltest.test;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import ru.terra.modeltest.core.WorldExecutor;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.AgentInfo;
import ru.terra.modeltest.core.agent.impl.FemaleAgent;
import ru.terra.modeltest.core.agent.impl.MaleAgent;
import ru.terra.modeltest.core.message.impl.FriendshipMessage;
import ru.terra.modeltest.gui.beans.FriendshipInfo;
import ru.terra.modeltest.storage.AgentsLoader;
import ru.terra.modeltest.storage.FriendsLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static ru.terra.modeltest.util.AgentUtil.parseAgentInfo;

public class AgentsTest {
    private Logger logger = Logger.getLogger(this.getClass());

    private Agent generateAgent(Boolean male, String name) {
        AgentInfo ai = new AgentInfo();
        ai.setName(name);
        ai.setFriends(new HashMap<>());
        Agent agent;
        if (male)
            agent = new MaleAgent();
        else
            agent = new FemaleAgent();
        agent.setInfo(ai);
        agent.setUid(UUID.randomUUID().toString());
        return agent;
    }

    @Test
    public void testRunAgents2() throws IOException {
        WorldExecutor.getInstance().getAgentsWorld();
        List<Agent> loadedAgents = new AgentsLoader().loadAgents("agents2.txt");
        Assert.assertNotNull(loadedAgents);
        loadedAgents.forEach(a -> WorldExecutor.getInstance().getAgentsWorld().addAgent(a));

        List<FriendshipInfo> friendshipInfo = new FriendsLoader().loadFriendships("friends2.txt", loadedAgents);
        Assert.assertNotNull(friendshipInfo);

        Map<String, Agent> agentsMap = WorldExecutor.getInstance().getAgentsWorld().getAgents();
        friendshipInfo.forEach(fi -> WorldExecutor.getInstance().getAgentsWorld().postMessageToBoard(
                new FriendshipMessage(
                        fi.from,
                        fi.to,
                        agentsMap.get(fi.from) instanceof MaleAgent
                )
        ));

        WorldExecutor.getInstance().getAgentsWorld().getAgents().forEach((uid, a) -> logger.info(parseAgentInfo(a, agentsMap)));
    }
}
