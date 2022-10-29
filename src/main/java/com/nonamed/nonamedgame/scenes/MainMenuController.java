package com.nonamed.nonamedgame.scenes;

import com.nonamed.nonamedgame.App;
import com.nonamed.nonamedgame.Config;
import com.nonamed.nonamedgame.StaticData;
import com.nonamed.nonamedgame.utils.HeroSoundService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    protected Button exitButton;

    @FXML
    protected void onNewGameButtonAsHarlan() {
        Config.isUsik = false;
        startGame();
    }
    @FXML
    protected void onNewGameButtonAsUsik() {
        Config.isUsik = true;
        startGame();
    }

    private void startGame() {
        App.MEDIA_PLAYER.stop();
        App.MEDIA_PLAYER = new MediaPlayer(StaticData.PlANE_SOUND);
        App.MEDIA_PLAYER.play();
        App.setStageScene(App.gamePane.getScene());
        App.getStage().setFullScreen(true);
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
