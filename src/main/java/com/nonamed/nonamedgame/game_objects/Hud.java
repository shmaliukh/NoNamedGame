package com.nonamed.nonamedgame.game_objects;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import static com.nonamed.nonamedgame.Config.HERO_ENERGY;
import static com.nonamed.nonamedgame.Config.HERO_HEALTH;
import static com.nonamed.nonamedgame.scenes.GameWorldHandler.HERO;
import static com.nonamed.nonamedgame.scenes.GameWorldHandler.gameWorld;

public final class Hud {

    public static final int AVATAR_HEIGHT = 128;
    public static final int AVATAR_WIDTH = 128;

    public static Group hudGroup = new Group();
    public static Line healthLine = new Line();
    public static Line energyLine = new Line();
    public static Text healthText = new Text("HEALTH");
    public static Text energyText = new Text("ENERGY");
    public static Text heroNameText = new Text("HERO NAME");
    public static Rectangle rectangle = new Rectangle();
    public static Rectangle avatar = new Rectangle();
    public static Text heroName = new Text();

    static {
        setUpRectangle();
        setUpAvatar();

        setUpHeroNameText();
        setUpHealthText();
        setUpEnergyText();
        setUpHealthLine();
        setUpEnergyLine();
    }

    private Hud() {

    }

    private static void setUpAvatar() {
        avatar.setLayoutX(603);
        avatar.setLayoutY(945);
        avatar.setHeight(AVATAR_HEIGHT);
        avatar.setWidth(AVATAR_WIDTH);
        avatar.setFill(Color.BLACK);
        hudGroup.getChildren().add(avatar);
    }

    private static void setUpHeroNameText() {
        heroNameText.setX(539);
        heroNameText.setY(930);
        heroNameText.setFont(Font.font(32));
        heroNameText.setWrappingWidth(256);
        heroNameText.setTextAlignment(TextAlignment.CENTER);
        hudGroup.getChildren().add(heroNameText);
    }

    private static void setUpRectangle() {
        rectangle.setLayoutX(0);
        rectangle.setLayoutY(802);
        rectangle.setWidth(1920);
        rectangle.setHeight(278);
        rectangle.setFill(Color.BROWN);
        hudGroup.getChildren().add(rectangle);
    }

    private static void setUpEnergyLine() {
        hudGroup.setLayoutX(0);
        hudGroup.setLayoutY(0);
        hudGroup.getChildren().add(healthText);
        hudGroup.getChildren().add(healthLine);
        hudGroup.getChildren().add(energyText);
        hudGroup.getChildren().add(energyLine);
    }

    private static void setUpHealthLine() {
        healthLine.setStrokeWidth(32);
        healthLine.setStroke(Color.GREEN);
        healthLine.setStartX(837);
        healthLine.setStartY(950);
        healthLine.setEndY(950);
        healthLine.setEndX(calcHeroHeath());
    }

    private static void setUpEnergyText() {
        energyLine.setStrokeWidth(32);
        energyLine.setStroke(Color.BLUE);
        energyLine.setStartX(837);
        energyLine.setStartY(1041);
        energyLine.setEndY(1041);
        energyLine.setEndX(calcHeroEnergy());
    }

    private static void setUpHealthText() {
        healthText.setX(837);
        healthText.setY(930);
        healthText.setFont(Font.font(32));
        healthText.setWrappingWidth(512.0);
        healthText.setTextAlignment(TextAlignment.CENTER);
        energyText.setX(837);
        energyText.setY(1022);
        energyText.setFont(Font.font(32));
        energyText.setWrappingWidth(512.0);
        energyText.setTextAlignment(TextAlignment.CENTER);
    }

    public static void updateGroup() {
        hudGroup.setLayoutX(-gameWorld.getGamePane().getLayoutX());
        hudGroup.setLayoutY(-gameWorld.getGamePane().getLayoutY());


        healthLine.setEndX(calcHeroHeath());
        energyLine.setEndX(calcHeroEnergy());
    }

    static int calcHeroHeath() {
        return 837 + 512 * HERO.getHealth() / HERO_HEALTH;
    }

    static int calcHeroEnergy() {
        return 837 + 512 * HERO.getEnergy() / HERO_ENERGY;
    }

}
