package com.nonamed.nonamedgame.scenes;

import com.nonamed.nonamedgame.App;
import com.nonamed.nonamedgame.Config;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsMenuController implements Initializable {

    @FXML
    protected Slider speedSlider;
    @FXML
    protected Slider heroDamageSlider;
    @FXML
    protected Slider heroHealthSlider;
    @FXML
    protected Slider enemySpeedSlider;
    @FXML
    protected Text speedValueText;
    @FXML
    protected Text heroDamageText;
    @FXML
    protected Text heroHealthText;
    @FXML
    protected Text enemySpeedText;


    @FXML
    protected void onSpeedSliderAction() {
//        ObjectInputFilter.Config.HERO_SPEED = speedSlider.valueProperty().getValue().intValue();
        // FIXME !!!!!!!!!!! set up hero speed
        speedValueText.setText(Config.HERO_SPEED + "");
    }

    @FXML
    protected void onHeroDamageSliderAction() {
        Config.HERO_DAMAGE = heroDamageSlider.valueProperty().getValue().intValue();
        heroDamageText.setText(Config.HERO_DAMAGE + "");
    }

    @FXML
    protected void onHeroHealthSliderAction() {
        Config.HERO_HEALTH = heroHealthSlider.valueProperty().getValue().intValue();
        heroHealthText.setText(Config.HERO_HEALTH + "");
    }

    @FXML
    protected void onEnemySpeedSliderAction() {
        Config.DARK_PERSON_SPEED = enemySpeedSlider.valueProperty().getValue().intValue();
        enemySpeedText.setText(Config.DARK_PERSON_SPEED + "");
    }


    @FXML
    protected void onMainMenuButtonClick() {
        Scene mainMenuScene = new MainMenu().mainMenuScene;
        App.stage.setScene(mainMenuScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setSliderAndTextValueFromConfig();
    }

    public void setSliderAndTextValueFromConfig() {
        speedSlider.setValue(Config.HERO_SPEED);
        speedValueText.setText(Config.HERO_SPEED + "");

        heroDamageSlider.setValue(Config.HERO_DAMAGE);
        heroDamageText.setText(Config.HERO_DAMAGE + "");

        heroHealthSlider.setValue(Config.HERO_HEALTH);
        heroHealthText.setText(Config.HERO_HEALTH + "");

        enemySpeedSlider.setValue(Config.DARK_PERSON_SPEED);
        enemySpeedText.setText(Config.DARK_PERSON_SPEED + "");
    }
}
