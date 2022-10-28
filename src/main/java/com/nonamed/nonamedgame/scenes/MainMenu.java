package com.nonamed.nonamedgame.scenes;

import com.nonamed.nonamedgame.App;
import com.nonamed.nonamedgame.utils.MyLogUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;

public final class MainMenu {

    private final FXMLLoader FXML_LOADER = new FXMLLoader(App.class.getResource("fxmls/mainMenu.fxml"));
    private Pane pane;
    public Scene mainMenuScene;

    public MainMenu() {
        initScene();
    }

    private void initScene() {
        try {
            pane = FXML_LOADER.load();
        } catch (IOException ioe) {
            MyLogUtil.logErr(this, ioe);
        }
        mainMenuScene = new Scene(pane);
    }

}
