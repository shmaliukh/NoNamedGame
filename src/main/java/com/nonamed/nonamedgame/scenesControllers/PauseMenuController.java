package com.nonamed.nonamedgame.scenesControllers;

import com.nonamed.nonamedgame.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PauseMenuController {


    @FXML
    public Button continueButton;
    @FXML
    public Button saveButton;
    @FXML
    public Button mainMenuButton;
    @FXML
    private void onMainMenuButtonClick(){
        System.out.println("new game");
        App.setStageScene(App.mainMenuPane.getScene());
    }

    @FXML
    private void onContinueButtonClick(){
        System.out.println("continue");
        App.setStageScene(App.gamePane.getScene());
        App.getStage().setFullScreen(true);
        App.isStopped = false;
        App.startGame();
    }


}