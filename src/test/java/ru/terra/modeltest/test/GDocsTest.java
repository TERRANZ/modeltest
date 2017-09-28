package ru.terra.modeltest.test;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import ru.terra.modeltest.core.WorldExecutor;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.impl.MaleAgent;
import ru.terra.modeltest.core.message.impl.FriendshipMessage;
import ru.terra.modeltest.gui.beans.FriendshipInfo;
import ru.terra.modeltest.storage.AgentsLoader;
import ru.terra.modeltest.storage.FriendsLoader;
import ru.terra.modeltest.storage.gdocs.GDocsSaver;

import java.util.List;
import java.util.Map;

import static ru.terra.modeltest.util.AgentUtil.parseAgentInfo;

public class GDocsTest {
    private Logger logger = Logger.getLogger(this.getClass());

    @Test
    public void listTest() throws Exception {
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

        WorldExecutor.getInstance().getAgentsWorld().recalcAbleFriends();

        WorldExecutor.getInstance().getAgentsWorld().getAgents().forEach((uid, a) -> logger.info(parseAgentInfo(a, agentsMap)));

        GDocsSaver saver = new GDocsSaver();
        saver.saveToGdocs(WorldExecutor.getInstance().getAgentsWorld().getAgents());
    }
}
