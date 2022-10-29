package com.nonamed.nonamedgame.scenes;

import com.nonamed.nonamedgame.App;
import com.nonamed.nonamedgame.StaticData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class PreviewController {

    @FXML
    public Button startGameButton;
    @FXML
    public Button mainMenuButton;

    @FXML
    private void onStartGameButtonClick(){
        App.MEDIA_PLAYER.stop();
        App.MEDIA_PLAYER = new MediaPlayer(StaticData.PlANE_SOUND);
        App.MEDIA_PLAYER.play();
        App.setStageScene(App.gamePane.getScene());
        App.getStage().setFullScreen(true);
        App.execute();
    }

    @FXML
    private void onMainMenuButtonClick(){
        App.setStageScene(App.mainMenuPane.getScene());
    }

}