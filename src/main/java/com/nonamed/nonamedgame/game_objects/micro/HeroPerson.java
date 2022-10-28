package com.nonamed.nonamedgame.game_objects.micro;

import com.nonamed.nonamedgame.App;
import com.nonamed.nonamedgame.Config;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static com.nonamed.nonamedgame.Config.HERO_DAMAGE;
import static com.nonamed.nonamedgame.Config.HERO_HEALTH;

public class HeroPerson extends AbstractPerson {

    public HeroPerson(String name, int posX, int posY) {
        this.name = name;
        this.health = HERO_HEALTH;
        this.damage = HERO_DAMAGE;
        this.posX = posX;
        this.posY = posY;

        setUpPersonPicturesGroup();
//        initTimerToMovePerson();
        setUpKeyHandlerActions(App.stage); // FIXME
    }

    @Override
    protected void setUpPersonPicturesGroup() {
        initImageView("heroLevel1.png");
        initNameText();
        initHealthLine(HERO_HEALTH, Color.GREEN);
    }

    public void setUpKeyHandlerActions(Stage scene) {
        onKeyPressed(scene);
        onKeyReleased(scene);
    }

    private void onKeyReleased(Stage scene) {
        scene.addEventFilter(KeyEvent.KEY_RELEASED, (keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP -> isUP = false;
                case DOWN -> isDOWN = false;
                case RIGHT -> isRIGHT = false;
                case LEFT -> isLEFT = false;
            }
            moveStatus = WAIT;
        }));
    }

    private void onKeyPressed(Stage scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP -> moveByDirection(MOVE_UP);
                case DOWN -> moveByDirection(MOVE_DOWN);
                case RIGHT -> moveByDirection(MOVE_RIGHT);
                case LEFT -> moveByDirection(MOVE_LEFT);
            }
        }));
    }

    private void moveByDirection(String moveDirectionStr) {
        if (!moveStatus.equals(moveDirectionStr)) {
            moveStatus = moveDirectionStr;
            switch (moveDirectionStr) {
                case MOVE_UP -> isUP = true;
                case MOVE_DOWN -> isDOWN = true;
                case MOVE_RIGHT -> isRIGHT = true;
                case MOVE_LEFT -> isLEFT = true;
            }
            //System.out.println("pos: " + posX + " | " + posY);
        }
    }

    protected void upDatePerson() {
        speed = Config.HERO_SPEED;
        upDateAllPersonImages(HERO_HEALTH);
    }


    public void move() {
        if (isUP) {
            setPosY(getPosY() - speed);
            upDatePerson();
        }
        if (isDOWN) {
            setPosY(getPosY() + speed);
            upDatePerson();
        }
        if (isRIGHT) {
            setPosX(getPosX() + speed);
            upDatePerson();
            imageView.setImage(new Image("heroLevel1Right.png"));
        }
        if (isLEFT) {
            setPosX(getPosX() - speed);
            upDatePerson();
            imageView.setImage(new Image("heroLevel1Left.png"));
        }
    }

}