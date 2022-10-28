package com.nonamed.nonamedgame.game_objects.micro;

import com.nonamed.nonamedgame.game_objects.BaseObject;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractPerson extends BaseObject {

    public static final String MOVE_UP = "moveUP";
    public static final String MOVE_DOWN = "moveDOWN";
    public static final String MOVE_RIGHT = "moveRIGHT";
    public static final String MOVE_LEFT = "moveLEFT";
    public static final String WAIT = "wait";

    protected String name;
    protected int health;
    protected int damage;
    protected int speed = 4;

    protected int posX;
    protected int posY;
    protected String moveStatus = "";
    protected AnimationTimer timer;

    protected Text nameText;
    protected Line healthLine;
    protected Group group = new Group();

    protected boolean isUP = false;
    protected boolean isDOWN = false;
    protected boolean isRIGHT = false;
    protected boolean isLEFT = false;

    protected void initImageView(String imageFileNameStr) {
        initImageView(imageFileNameStr, 0, 0);
        group.getChildren().add(imageView);
    }

    public abstract void move();

    protected void initHealthLine(int maxHealth, Color color) {
        healthLine = new Line(0, 0, calcHealthPosXEnd(maxHealth), 0);
        healthLine.setStrokeWidth(5);
        healthLine.setStroke(color);
        group.getChildren().add(healthLine);
    }

    protected void initNameText() {
        nameText = new Text(name);
        nameText.setX(0);
        nameText.setY(-10);
        nameText.setFont(Font.font(12));
        group.getChildren().add(nameText);
    }

    protected void upDateAllPersonImages(int maxHealth) {
        group.setLayoutX(getPosX());
        group.setLayoutY(getPosY());
        healthLine.setEndX(calcHealthPosXEnd(maxHealth));
    }

    private int calcHealthPosXEnd(int maxHealth) {
        return 100 * getHealth() / maxHealth;
    }

    protected void initTimerToMovePerson() {// 'l' is default variable
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                move();
            }
        };
        timer.start();
    }

    protected abstract void upDatePerson();

    protected abstract void setUpPersonPicturesGroup();

}
