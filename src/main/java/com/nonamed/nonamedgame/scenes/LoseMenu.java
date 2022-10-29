package com.nonamed.nonamedgame.scenes;

import com.nonamed.nonamedgame.App;
import com.nonamed.nonamedgame.utils.MyLogUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;

public final class LoseMenu {

    public static final FXMLLoader FXML_LOADER = new FXMLLoader(App.class.getResource("fxmls/loseMenu.fxml"));
    public static Pane pane;
    public static Scene loseMenuScene;


    static  {
        try {
            pane = FXML_LOADER.load();
        } catch (IOException ioe) {
//            MyLogUtil.logErr(this, ioe);
        }
        loseMenuScene = new Scene(pane);
    }

}
