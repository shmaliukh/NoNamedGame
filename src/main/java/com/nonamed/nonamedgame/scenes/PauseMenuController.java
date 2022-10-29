package com.nonamed.nonamedgame.scenes;

import com.nonamed.nonamedgame.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;


public class PauseMenuController implements Initializable {

    @FXML
    protected void onContinueButtonClick() {
        App.setStageScene(App.gamePane.getScene());
        App.startGame();
    }

    @FXML
    protected void onMainMenuButtonClick() {
        App.setStageScene(App.mainMenuPane.getScene());
    }

    @FXML
    protected void onRetryButtonClick() {
        App.setStageScene(App.gamePane.getScene());
        App.execute();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
