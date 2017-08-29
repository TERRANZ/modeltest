package ru.terra.modeltest.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import ru.terra.modeltest.gui.parts.AbstractDialog;
import ru.terra.modeltest.gui.parts.NewAgentInfo;

import java.net.URL;
import java.util.ResourceBundle;

public class AddAgentController extends AbstractDialog<NewAgentInfo> {
    @FXML
    public TextField tfName;
    @FXML
    public CheckBox cbMale;

    @Override
    public void ok(ActionEvent actionEvent) {
        returnValue = new NewAgentInfo();
        returnValue.male = cbMale.isSelected();
        returnValue.name = tfName.getText();
        if (dialogIsDoneListener != null)
            dialogIsDoneListener.dialogIsDone(returnValue);
        currStage.close();
    }

    @Override
    public void loadExisting(NewAgentInfo value) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
