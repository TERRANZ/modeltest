package ru.terra.modeltest;

import ru.terra.modeltest.core.AgentsWorld;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.AgentInfo;
import ru.terra.modeltest.core.agent.impl.FemaleAgent;
import ru.terra.modeltest.core.agent.impl.MaleAgent;
import ru.terra.modeltest.gui.AbstractUIController;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.UUID;

public class MainController extends AbstractUIController {
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(() -> {
            final AgentsWorld agentsWorld = new AgentsWorld();
            for (int i = 1; i < 4; i++) {
                AgentInfo ai = new AgentInfo();
                ai.setName("Agent " + i);
                ai.setUid(UUID.randomUUID().toString());
                ai.setFriends(new HashMap<>());
                if ((new Date().getTime() & 1) == 0) {
                    Agent fa = new FemaleAgent();
                    fa.setInfo(ai);
                    agentsWorld.addAgent(fa);
                } else {
                    Agent ma = new MaleAgent();
                    ma.setInfo(ai);
                    agentsWorld.addAgent(ma);
                }
            }
        }).start();
    }
}
