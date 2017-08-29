package ru.terra.modeltest.gui;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Pair;
import ru.terra.modeltest.core.WorldExecutor;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.AgentInfo;
import ru.terra.modeltest.core.agent.impl.FemaleAgent;
import ru.terra.modeltest.core.agent.impl.MaleAgent;
import ru.terra.modeltest.gui.parts.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class MainController extends AbstractUIController {
    public void initialize(URL location, ResourceBundle resources) {
        WorldExecutor.getInstance().getAgentsWorld();
    }

    public void showDebug(ActionEvent actionEvent) {
        StageHelper.openWindow("w_debug.fxml", "Debug", false);
    }

    public void load(ActionEvent actionEvent) {

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
}
