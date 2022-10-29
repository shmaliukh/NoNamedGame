package com.nonamed.nonamedgame.game_objects;

import com.nonamed.nonamedgame.StaticData;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import static com.nonamed.nonamedgame.App.HERO;
import static com.nonamed.nonamedgame.Config.*;
import static com.nonamed.nonamedgame.GameWorld.miniMap;
import static com.nonamed.nonamedgame.StaticData.HUD;

public final class Hud {

    public static final String SCORE_ENG = "SCORE:";
    public static final String HEALTH_ENG = "HEALTH";
    public static final String ENERGY_ENG = "ENERGY";
    public static final String HERO_NAME_ENG = "HERO NAME";

    public static final String SCORE_UKR = "БАЛИ:";
    public static final String HEALTH_UKR = "ЗДОРОВ'Я";
    public static final String ENERGY_UKR = "ЕНЕРГІЯ";
    public static final String HERO_NAME_UKR = "КОЗАК";

    public static Group hudGroup = new Group();
    public static Rectangle scoreRectangle = new Rectangle();
    public static Line healthLine = new Line();
    public static Line energyLine = new Line();
    public static Line energyBackLine = new Line();
    public static Line healthBackLine = new Line();
    public static Text scoreText = new Text(SCORE_UKR);
    public static Text currentScoreText = new Text("0");
    public static Text healthText = new Text(HEALTH_UKR);
    public static Text energyText = new Text(ENERGY_UKR);
    public static Text heroNameText = new Text(HERO_NAME_UKR);
    public static ImageView hudImage = new ImageView();
    public static ImageView scoreImageFrame = new ImageView();
    public static ImageView avatar = new ImageView();
    public static Text heroName = new Text();

    private Hud() {

    }

    static int calcHeroHeath() {
        return 768 + 512 * HERO.getHealth() / HERO_HEALTH;
    }

    static int calcHeroEnergy() {
        return 768 + 512 * HERO.getEnergy() / HERO_ENERGY;
    }

    public static Group generateHudGroup() {
        hudGroup = new Group();
        initHud();
        return hudGroup;
    }

    static void initHud() {
        setUpAvatar();

        initSCoreRectangle();

        setUpScoreText();
        setUpCurrentScoreText();


        setUpHealthBackLine();
        setUpEnergyBackLine();

        setUpHealthLine();
        setUpEnergyLine();

        hudGroup.getChildren().add(miniMap);

        setScoreFrame();
        setUpHud();

        setUpHeroNameText();
        setUpHealthText();
        setUpEnergyText();


        hudGroup.setLayoutX(0);
        hudGroup.setLayoutY(0);

    }

    private static void setUpCurrentScoreText() {
        currentScoreText.setX(960);
        currentScoreText.setY(40);
        currentScoreText.setFont(Font.font(32));
        currentScoreText.setWrappingWidth(128);
        currentScoreText.setTextAlignment(TextAlignment.CENTER);
        hudGroup.getChildren().add(currentScoreText);
    }

    private static void setUpScoreText() {
        scoreText.setX(832);
        scoreText.setY(40);
        scoreText.setFont(Font.font(32));
        scoreText.setWrappingWidth(128);
        scoreText.setTextAlignment(TextAlignment.CENTER);
        hudGroup.getChildren().add(scoreText);
    }

    private static void initSCoreRectangle() {
        scoreRectangle = new Rectangle();
        scoreRectangle.setLayoutX(832);
        scoreRectangle.setLayoutY(0);
        scoreRectangle.setFill(Color.BISQUE);
        scoreRectangle.setWidth(256);
        scoreRectangle.setHeight(60);
        hudGroup.getChildren().add(scoreRectangle);
    }

    private static void setUpAvatar() {
        avatar.setImage(HERO_AVATAR);
        avatar.setLayoutX(616);
        avatar.setLayoutY(888);
        hudGroup.getChildren().add(avatar);
    }

    private static void setUpHealthBackLine() {
        healthBackLine.setStrokeWidth(32);
        healthBackLine.setStroke(Color.BISQUE);
        healthBackLine.setStartX(790);
        healthBackLine.setStartY(930);
        healthBackLine.setEndY(930);
        healthBackLine.setEndX(790 + 512);
        hudGroup.getChildren().add(healthBackLine);
    }

    private static void setUpEnergyBackLine() {
        energyBackLine.setStrokeWidth(32);
        energyBackLine.setStroke(Color.BISQUE);
        energyBackLine.setStartX(790);
        energyBackLine.setStartY(1018);
        energyBackLine.setEndY(1018);
        energyBackLine.setEndX(790 + 512);
        hudGroup.getChildren().add(energyBackLine);
    }

    public static void setUpEnergyLine() {
        energyLine.setStrokeWidth(32);
        energyLine.setStroke(Color.BLUE);
        energyLine.setStartX(790);
        energyLine.setStartY(1018);
        energyLine.setEndY(1018);
        energyLine.setEndX(790 + 512);
        hudGroup.getChildren().add(energyLine);
    }

    public static void setUpHealthLine() {
        healthLine.setStrokeWidth(32);
        healthLine.setStroke(Color.GREEN);
        healthLine.setStartX(790);
        healthLine.setStartY(930);
        healthLine.setEndY(930);
        healthLine.setEndX(790 + 512);
        hudGroup.getChildren().add(healthLine);

    }

    private static void setUpHeroNameText() {
        heroNameText.setX(552);
        heroNameText.setY(868);
        heroNameText.setFont(Font.font(32));
        heroNameText.setWrappingWidth(256);
        heroNameText.setTextAlignment(TextAlignment.CENTER);
        hudGroup.getChildren().add(heroNameText);
    }

    private static void setUpHud() {
        hudImage.setLayoutX(0);
        hudImage.setLayoutY(765);
//        hudImage.setFitWidth(1920);
//        hudImage.setFitHeight(300);
        hudImage.setImage(HUD);
        hudGroup.getChildren().add(hudImage);
    }

    private static void setScoreFrame() {
        scoreImageFrame.setLayoutX(815);
        scoreImageFrame.setLayoutY(0);
        scoreImageFrame.setImage(StaticData.SCORE_IMAGE);
        hudGroup.getChildren().add(scoreImageFrame);
    }

    private static void setUpEnergyText() {
        energyText.setX(790);
        energyText.setY(990);
        energyText.setFont(Font.font(32));
        energyText.setWrappingWidth(512.0);
        energyText.setTextAlignment(TextAlignment.CENTER);
        hudGroup.getChildren().add(energyText);

    }

    private static void setUpHealthText() {
        healthText.setX(790);
        healthText.setY(900);
        healthText.setFont(Font.font(32));
        healthText.setWrappingWidth(512.0);
        healthText.setTextAlignment(TextAlignment.CENTER);
        hudGroup.getChildren().add(healthText);
    }

    public static void updateGroup() {
        hudGroup.toFront();
//        hudGroup.setLayoutX(-gameWorld..getLayoutX());
//        hudGroup.setLayoutY(-gameWorld.getGamePane().getLayoutY());

        currentScoreText.setText(String.valueOf(HERO.SCORE));
        healthLine.setEndX(calcHeroHeath());
        energyLine.setEndX(calcHeroEnergy());
    }

}
