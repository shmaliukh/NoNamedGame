package com.nonamed.nonamedgame.scenes;

import com.nonamed.nonamedgame.App_old;
import com.nonamed.nonamedgame.Config;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
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
    protected Slider volumeSlider;
    @FXML
    protected Text speedValueText;
    @FXML
    protected Text heroDamageText;
    @FXML
    protected Text heroHealthText;
    @FXML
    protected Text enemySpeedText;
    @FXML
    public Text volumeSliderText;
    @FXML
    public CheckBox modeOne;
    @FXML
    public CheckBox modeTwo;
    @FXML
    public CheckBox modeThree;
    @FXML
    public CheckBox modeFour;
    @FXML
    public CheckBox modeFive;



    @FXML
    protected void onSpeedSliderAction() {
        Config.HERO_SPEED = speedSlider.valueProperty().getValue().intValue();
        speedValueText.setText(Config.HERO_SPEED + "");
    }

    @FXML
    protected void onVolumeSliderAction(){
        Config.VOLUME_LEVEL = volumeSlider.valueProperty().getValue()/10;
        volumeSliderText.setText(volumeSlider.getValue() + "");
        App_old.MEDIA_PLAYER.setVolume(Config.VOLUME_LEVEL);
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
    protected void onChooseModeBoxActionLight(){
        modeTwo.setSelected(false);
        modeThree.setSelected(false);
        modeFour.setSelected(false);
        modeFive.setSelected(false);
        setSliderDisabled();
        setConfigFileLight();
        setSliderAndTextValueFromConfig();
    }

    @FXML
    protected void onChooseModeBoxActionMedium(){
        modeOne.setSelected(false);
        modeThree.setSelected(false);
        modeFour.setSelected(false);
        modeFive.setSelected(false);
        setSliderDisabled();
        setConfigFileMedium();
        setSliderAndTextValueFromConfig();
    }

    @FXML
    protected void onChooseModeBoxActionHard(){
        modeOne.setSelected(false);
        modeTwo.setSelected(false);
        modeFour.setSelected(false);
        modeFive.setSelected(false);
        setSliderDisabled();
        setConfigFileHard();
        setSliderAndTextValueFromConfig();
    }

    @FXML
    protected void onChooseModeBoxActionFour(){
        modeOne.setSelected(false);
        modeTwo.setSelected(false);
        modeThree.setSelected(false);
        modeFive.setSelected(false);
        setSliderDisabled();
        setConfigFileEpic();
        setSliderAndTextValueFromConfig();
    }
    @FXML
    protected void onChooseModeBoxActionFive(){
        modeOne.setSelected(false);
        modeTwo.setSelected(false);
        modeThree.setSelected(false);
        modeFour.setSelected(false);
        setConfigFilePersonal();
        setSliderEnabled();
    }

    @FXML
    protected void onEnemySpeedSliderAction() {
        Config.DARK_PERSON_SPEED = enemySpeedSlider.valueProperty().getValue().intValue();
        enemySpeedText.setText(Config.DARK_PERSON_SPEED + "");
    }


    @FXML
    protected void onMainMenuButtonClick() {
//        Scene mainMenuScene = new MainMenu().mainMenuScene;
        App_old.setStageScene(App_old.mainMenuPane.getScene());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setSliderAndTextValueFromConfig();
        modeOne.fire();
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

        volumeSlider.setValue(Config.VOLUME_LEVEL * 10);
        volumeSliderText.setText(volumeSlider.getValue() + "");
    }

    public void setSliderDisabled(){
        speedSlider.setDisable(true);
        heroDamageSlider.setDisable(true);
        heroHealthSlider.setDisable(true);
        enemySpeedSlider.setDisable(true);
    }
    public void setSliderEnabled(){
        speedSlider.setDisable(false);
        heroDamageSlider.setDisable(false);
        heroHealthSlider.setDisable(false);
        enemySpeedSlider.setDisable(false);
    }


    public void setConfigFileLight() {
        Config.HERO_SPEED = 8;
        Config.HERO_DAMAGE = 100;
        Config.HERO_HEALTH = 10000;
        Config.DARK_PERSON_SPEED = 3;
    }

    public void setConfigFileMedium() {
        Config.HERO_SPEED = 6;
        Config.HERO_DAMAGE = 60;
        Config.HERO_HEALTH =  7000;
        Config.DARK_PERSON_SPEED = 4;
    }

    public void setConfigFileHard() {
        Config.HERO_SPEED = 5;
        Config.HERO_DAMAGE = 40;
        Config.HERO_HEALTH =  2000;
        Config.DARK_PERSON_SPEED = 6;
    }

    public void setConfigFilePersonal() {
        setSliderEnabled();
    }
    public void setConfigFileEpic() {
        Config.HERO_SPEED = 5;
        Config.HERO_DAMAGE = 100;
        Config.HERO_HEALTH =  20000;
        Config.DARK_PERSON_SPEED = 4;
    }
}
