package com.nonamed.nonamedgame.scenes;

import com.nonamed.nonamedgame.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import java.net.URL;
import java.util.ResourceBundle;

public class LoseMenuController implements Initializable {

    @FXML
    protected void onMainMenuButtonClick() {
        Scene mainMenuScene = new MainMenu().mainMenuScene;
        App.stage.setScene(mainMenuScene);
    }

    @FXML
    protected void onRetryButtonClick() {
        Scene gameScene = new GameWorldHandler().gameWorld.getGameScene();
        App.stage.setScene(gameScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
