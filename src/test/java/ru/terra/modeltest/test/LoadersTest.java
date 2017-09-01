package ru.terra.modeltest.test;

import org.junit.Assert;
import org.junit.Test;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.gui.beans.FriendshipInfo;
import ru.terra.modeltest.storage.AgentsLoader;
import ru.terra.modeltest.storage.FriendsLoader;

import java.io.IOException;
import java.util.List;

public class LoadersTest {
    @Test
    public void loadAgentsTest() throws IOException {
        List<Agent> loadedAgents = new AgentsLoader().loadAgents("agents1.txt");
        Assert.assertNotNull(loadedAgents);
        Assert.assertEquals(3, loadedAgents.size());
    }

    @Test
    public void loadFriendShipsTest() throws IOException {
        List<Agent> loadedAgents = new AgentsLoader().loadAgents("agents2.txt");
        Assert.assertNotNull(loadedAgents);
        Assert.assertEquals(16, loadedAgents.size());

        List<FriendshipInfo> friendshipInfos1 = new FriendsLoader().loadFriendships("friends2.txt", loadedAgents);
        Assert.assertNotNull(friendshipInfos1);
        Assert.assertEquals(14, friendshipInfos1.size());

    }
}
