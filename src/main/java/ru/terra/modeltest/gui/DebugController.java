package ru.terra.modeltest.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import ru.terra.modeltest.core.WorldExecutor;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.gui.parts.AbstractUIController;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class DebugController extends AbstractUIController {
    @FXML
    public ListView<String> lvDebug;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void refresh(ActionEvent actionEvent) {
        lvDebug.getItems().clear();
        List<Agent> agents = WorldExecutor.getInstance().getAgentsWorld().getAgents();
        Map<String, Agent> agentsMap = new HashMap<>();
        agents.forEach(a -> agentsMap.put(a.getInfo().getUid(), a));
        agents.forEach(a -> lvDebug.getItems().add(parseAgentInfo(a, agentsMap)));
    }

    private String parseAgentInfo(Agent agent, Map<String, Agent> agentsMap) {
        StringBuilder sb = new StringBuilder().append("Agent: ").append(agent.getInfo().getName()).append(" friends: ").append(agent.getInfo().getFriends().size());
        agent.getInfo().getFriends().keySet().forEach(uid -> sb.append(", ").append(agentsMap.get(uid).getInfo().getName()));
        return sb.toString();
    }
}
