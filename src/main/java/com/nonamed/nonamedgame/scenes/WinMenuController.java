package com.nonamed.nonamedgame.scenes;

import com.nonamed.nonamedgame.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class WinMenuController implements Initializable {

    @FXML
    protected void onMainMenuButtonClick() {
//        Scene mainMenuScene = new MainMenu().mainMenuScene;
        App.setStageScene(App.mainMenuPane.getScene());
    }

    @FXML
    protected void onRetryButtonClick() {
        App.setStageScene(App.gamePane.getScene());
        App.execute();
//        Scene gameScene = App_old.gameWorld.getGameScene();
//        App_old.stage.setScene(gameScene);
//        App_old.stage.setFullScreen(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
