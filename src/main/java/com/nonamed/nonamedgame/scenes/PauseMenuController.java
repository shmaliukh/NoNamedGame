package com.nonamed.nonamedgame.scenes;

import com.nonamed.nonamedgame.App_old;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;


public class PauseMenuController implements Initializable {

    @FXML
    protected void onContinueButtonClick() {
        App_old.setStageScene(App_old.gamePane.getScene());
        App_old.startGame();
    }

    @FXML
    protected void onMainMenuButtonClick() {
        App_old.setStageScene(App_old.mainMenuPane.getScene());
    }

    @FXML
    protected void onRetryButtonClick() {
        App_old.setStageScene(App_old.gamePane.getScene());
        App_old.execute();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
