package com.nonamed.nonamedgame.scenes;

import com.nonamed.nonamedgame.App_old;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class WinMenuController implements Initializable {

    @FXML
    protected void onMainMenuButtonClick() {
//        Scene mainMenuScene = new MainMenu().mainMenuScene;
        App_old.setStageScene(App_old.mainMenuPane.getScene());
    }

    @FXML
    protected void onRetryButtonClick() {
        App_old.setStageScene(App_old.gamePane.getScene());
        App_old.execute();
//        Scene gameScene = App_old.gameWorld.getGameScene();
//        App_old.stage.setScene(gameScene);
//        App_old.stage.setFullScreen(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
