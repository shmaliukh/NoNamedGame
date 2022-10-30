package com.nonamed.nonamedgame.scenes;

import com.nonamed.nonamedgame.App_old;
import com.nonamed.nonamedgame.Config;
import com.nonamed.nonamedgame.StaticData;
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
//        startGame();
        App_old.setStageScene(App_old.previewPane.getScene());
    }
    @FXML
    protected void onNewGameButtonAsUsik() {
        Config.isUsik = true;
        App_old.setStageScene(App_old.previewPane.getScene());
//        startGame();
    }

    private void startGame() {
        App_old.MEDIA_PLAYER.stop();
        App_old.MEDIA_PLAYER = new MediaPlayer(StaticData.PlANE_SOUND);
        App_old.MEDIA_PLAYER.play();
        App_old.setStageScene(App_old.gamePane.getScene());
        App_old.getStage().setFullScreen(true);
        App_old.execute();
    }

    @FXML
    protected void onSettingsButtonClick() {
        App_old.getStage().setScene(App_old.settingMenuPane.getScene());
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
