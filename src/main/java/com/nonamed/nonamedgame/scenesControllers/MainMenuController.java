package com.nonamed.nonamedgame.scenesControllers;

import com.nonamed.nonamedgame.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController{


    @FXML
    public Button newGameButton;
    @FXML
    public Button settingsButton;
    @FXML
    public Button exitButton;

    @FXML
    private void onNewGameButtonClick(){
        App.setStageScene(App.gamePane.getScene());
        App.getStage().setFullScreen(true);
        App.execute();
    }

    @FXML
    private void onExitButtonClick(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }


}