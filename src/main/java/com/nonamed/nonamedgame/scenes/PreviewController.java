package com.nonamed.nonamedgame.scenes;

import com.nonamed.nonamedgame.App_old;
import com.nonamed.nonamedgame.StaticData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;

public class PreviewController {

    @FXML
    public Button startGameButton;
    @FXML
    public Button mainMenuButton;

    @FXML
    private void onStartGameButtonClick(){
        App_old.MEDIA_PLAYER.stop();
        App_old.MEDIA_PLAYER = new MediaPlayer(StaticData.PlANE_SOUND);
        App_old.MEDIA_PLAYER.play();
        App_old.setStageScene(App_old.gamePane.getScene());
        App_old.getStage().setFullScreen(true);
        App_old.execute();
    }

    @FXML
    private void onMainMenuButtonClick(){
        App_old.setStageScene(App_old.mainMenuPane.getScene());
    }

}