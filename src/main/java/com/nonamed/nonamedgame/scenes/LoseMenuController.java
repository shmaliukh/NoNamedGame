package com.nonamed.nonamedgame.scenes;

import com.nonamed.nonamedgame.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class LoseMenuController implements Initializable {

    @FXML
    protected void onMainMenuButtonClick() {
        App.getStage().setScene(App.resultMenuPane.getScene());
    }

    @FXML
    protected void onRetryButtonClick() {
        App.setStageScene(App.gamePane.getScene());
        App.execute();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
