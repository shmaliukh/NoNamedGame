package com.nonamed.nonamedgame.scenes;

import com.nonamed.nonamedgame.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import java.net.URL;
import java.util.ResourceBundle;

public class WinMenuController implements Initializable {

    @FXML
    protected void onMainMenuButtonClick() {
        Scene mainMenuScene = new MainMenu().mainMenuScene;
        App.stage.setScene(mainMenuScene);
    }

    @FXML
    protected void onRetryButtonClick() {
        Scene gameScene = App.gameWorld.getGameScene();
        App.stage.setScene(gameScene);
        App.stage.setFullScreen(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
