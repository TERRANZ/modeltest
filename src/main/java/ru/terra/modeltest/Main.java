package ru.terra.modeltest;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.terra.modeltest.gui.StageHelper;

public class Main extends Application {
    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StageHelper.openWindow("w_main.fxml", "Main", true);
    }

}
