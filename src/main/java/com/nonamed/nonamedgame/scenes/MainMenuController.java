package com.nonamed.nonamedgame.scenes;

import com.nonamed.nonamedgame.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    protected Button exitButton;

    @FXML
    protected void onNewGameButtonClick() {
        App.setStageScene(App.gamePane.getScene());
        App.execute();
    }

    @FXML
    protected void onSettingsButtonClick() {
        App.getStage().setScene(App.settingMenuPane.getScene());
    }

    @FXML
    protected void onCloseButtonClick(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
