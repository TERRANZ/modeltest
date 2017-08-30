package ru.terra.modeltest.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import ru.terra.modeltest.core.WorldExecutor;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.AgentInfo;
import ru.terra.modeltest.core.agent.impl.FemaleAgent;
import ru.terra.modeltest.core.agent.impl.MaleAgent;
import ru.terra.modeltest.core.message.impl.DirectFriendMessage;
import ru.terra.modeltest.gui.beans.FriendshipInfo;
import ru.terra.modeltest.gui.beans.NewAgentInfo;
import ru.terra.modeltest.gui.parts.AbstractDialog;
import ru.terra.modeltest.gui.parts.AbstractUIController;
import ru.terra.modeltest.gui.parts.DialogIsDoneListener;
import ru.terra.modeltest.gui.parts.StageHelper;
import ru.terra.modeltest.storage.AgentsLoader;
import ru.terra.modeltest.storage.FriendsLoader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainController extends AbstractUIController {
    @FXML
    public ListView<String> lvAgents;
    @FXML
    public ListView<String> lvFriendships;
    @FXML
    public ListView<String> lvCurrentState;

    private List<FriendshipInfo> friendsInfo;

    public void initialize(URL location, ResourceBundle resources) {
        WorldExecutor.getInstance().getAgentsWorld();
    }

    public void showDebug(ActionEvent actionEvent) {
        StageHelper.openWindow("w_debug.fxml", "Debug", false);
    }

    public void save(ActionEvent actionEvent) {

    }

    public void close(ActionEvent actionEvent) {
        currStage.close();
    }

    public void addAgent(ActionEvent actionEvent) {
        Pair<Stage, AbstractDialog> dialog = StageHelper.openDialog("d_add_agent.fxml", "Add agent", currStage);
        dialog.getValue().setDialogIsDoneListener(new DialogIsDoneListener<NewAgentInfo>() {
            @Override
            public void dialogIsDone(NewAgentInfo ret, String... strings) {
                AgentInfo ai = new AgentInfo();
                ai.setUid(UUID.randomUUID().toString());
                ai.setName(ret.name);
                Agent agent;
                if (ret.male)
                    agent = new MaleAgent();
                else
                    agent = new FemaleAgent();

                agent.setInfo(ai);
                WorldExecutor.getInstance().getAgentsWorld().addAgent(agent);
            }

            @Override
            public void cancel() {

            }
        });
    }

    public void loadAgents(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Agents file", "*.txt"));
        fileChooser.setTitle("Open agents file");
        File agentsFile = fileChooser.showOpenDialog(currStage);
        if (agentsFile != null) {
            try {
                List<Agent> agentList = new AgentsLoader().loadAgents(agentsFile.getAbsolutePath());
                if (agentList != null) {
                    agentList.forEach(a -> WorldExecutor.getInstance().getAgentsWorld().addAgent(a));
                    lvAgents.getItems().clear();
                    agentList.forEach(a -> lvAgents.getItems().add(a.getInfo().getName()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadFriends(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Friends file", "*.txt"));
        fileChooser.setTitle("Open frinds file");
        File friendsFile = fileChooser.showOpenDialog(currStage);
        if (friendsFile != null) {
            try {
                List<Agent> agents = WorldExecutor.getInstance().getAgentsWorld().getAgents();
                Map<String, String> agentMap = new HashMap<>();
                agents.forEach(a -> agentMap.put(a.getInfo().getUid(), a.getInfo().getName()));
                friendsInfo = new FriendsLoader().loadFriendships(friendsFile.getAbsolutePath(), agents);
                if (friendsInfo != null) {
                    lvFriendships.getItems().clear();
                    friendsInfo.forEach(f -> lvFriendships.getItems().add(agentMap.get(f.from) + " <> " + agentMap.get(f.to)));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String parseAgentInfo(Agent agent, Map<String, Agent> agentsMap) {
        StringBuilder sb = new StringBuilder().append("Agent: ").append(agent.getInfo().getName()).append(" friends: ").append(agent.getInfo().getFriends().size());
        agent.getInfo().getFriends().keySet().forEach(uid -> sb.append(", ").append(agentsMap.get(uid).getInfo().getName()));
        return sb.toString();
    }

    public void runWork(ActionEvent actionEvent) {
        if (friendsInfo.size() > 0) {
            Map<String, Agent> agentsMap = new HashMap<>();
            WorldExecutor.getInstance().getAgentsWorld().getAgents().forEach(a -> agentsMap.put(a.getInfo().getUid(), a));
            friendsInfo.forEach(fi -> WorldExecutor.getInstance().getAgentsWorld().postMessageToBoard(
                    new DirectFriendMessage(
                            fi.from,
                            fi.to,
                            agentsMap.get(fi.from).getClass()
                    )
            ));

        }
    }

    public void refresh(ActionEvent actionEvent) {
        lvCurrentState.getItems().clear();
        List<Agent> agents = WorldExecutor.getInstance().getAgentsWorld().getAgents();
        Map<String, Agent> agentsMap = new HashMap<>();
        agents.forEach(a -> agentsMap.put(a.getInfo().getUid(), a));
        agents.forEach(a -> lvCurrentState.getItems().add(parseAgentInfo(a, agentsMap)));
    }
}
