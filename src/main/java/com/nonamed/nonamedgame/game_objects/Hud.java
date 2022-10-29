package com.nonamed.nonamedgame.game_objects;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import static com.nonamed.nonamedgame.App.HERO;
import static com.nonamed.nonamedgame.Config.*;
import static com.nonamed.nonamedgame.GameWorld.miniMap;
import static com.nonamed.nonamedgame.StaticData.HUD;

public final class Hud {

    public static final int AVATAR_HEIGHT = 128;
    public static final int AVATAR_WIDTH = 128;

    public static Group hudGroup = new Group();
    public static Line healthLine = new Line();
    public static Line energyLine = new Line();
    public static Line energyBackLine = new Line();
    public static Line healthBackLine = new Line();
    public static Text healthText = new Text("HEALTH");
    public static Text energyText = new Text("ENERGY");
    public static Text heroNameText = new Text("HERO NAME");
    public static ImageView hudImage = new ImageView();
    public static ImageView avatar = new ImageView();
    public static Text heroName = new Text();

    private Hud() {

    }

    //
    static int calcHeroHeath() {
        return 790 + 512 * HERO.getHealth() / HERO_HEALTH;
    }

    static int calcHeroEnergy() {
        return 790 + 512 * HERO.getEnergy() / HERO_ENERGY;
    }

    public static Group generateHudGroup() {
        hudGroup = new Group();
        initHud();
        return hudGroup;
    }

    static void initHud() {
        setUpAvatar();

        setUpHealthBackLine();
        setUpEnergyBackLine();

        setUpHealthLine();
        setUpEnergyLine();

        hudGroup.getChildren().add(miniMap);

        setUpHud();

        setUpHeroNameText();
        setUpHealthText();
        setUpEnergyText();


        hudGroup.setLayoutX(0);
        hudGroup.setLayoutY(0);

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

        healthLine.setEndX(calcHeroHeath());
        energyLine.setEndX(calcHeroEnergy());
    }

}
