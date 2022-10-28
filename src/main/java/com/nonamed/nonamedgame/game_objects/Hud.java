package com.nonamed.nonamedgame.game_objects;

import com.nonamed.nonamedgame.game_objects.micro.AbstractPerson;
import com.nonamed.nonamedgame.scenes.GameWorldHandler;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import static com.nonamed.nonamedgame.Config.HERO_ENERGY;
import static com.nonamed.nonamedgame.Config.HERO_HEALTH;
import static com.nonamed.nonamedgame.scenes.GameWorldHandler.HERO;
import static com.nonamed.nonamedgame.scenes.GameWorldHandler.gameWorld;

public final class Hud{

    public static Group hudGroup = new Group();
    public static Line healthLine = new Line();
    public static Line energyLine = new Line();
    public static Text healthText = new Text("HEALTH");
    public static Text energyText = new Text("ENERGY");
    public static Text heroName = new Text();


    static {

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

        energyLine.setStrokeWidth(32);
        energyLine.setStroke(Color.BLUE);
        energyLine.setStartX(837);
        energyLine.setStartY(1041);
        energyLine.setEndY(1041);
        energyLine.setEndX(calcHeroEnergy());

        healthLine.setStrokeWidth(32);
        healthLine.setStroke(Color.GREEN);
        healthLine.setStartX(837);
        healthLine.setStartY(950);
        healthLine.setEndY(950);
        healthLine.setEndX(calcHeroHeath());


        hudGroup.setLayoutX(0);
        hudGroup.setLayoutY(0);
        hudGroup.getChildren().add(healthText);
        hudGroup.getChildren().add(healthLine);
        hudGroup.getChildren().add(energyText);
        hudGroup.getChildren().add(energyLine);
    }

    private Hud() {

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
