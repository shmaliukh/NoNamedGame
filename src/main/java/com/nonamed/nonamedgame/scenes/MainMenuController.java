package com.nonamed.nonamedgame.scenes;

import com.nonamed.nonamedgame.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    protected Button exitButton;

    @FXML
    protected void onNewGameButtonClick() {
        Scene gameScene = App.gameWorld.getGameScene();
        App.stage.setScene(gameScene);
//        App.stage.setFullScreen(true);
    }

    @FXML
    protected void onSettingsButtonClick() {
//        App.stage.getScene().setRoot(new SettingsMenu().pane);
        App.stage.setScene(new SettingsMenu().settingsMenuScene);
    }

    @FXML
    protected void onCloseButtonClick(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
