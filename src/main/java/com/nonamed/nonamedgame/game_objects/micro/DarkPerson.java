package com.nonamed.nonamedgame.game_objects.micro;

import com.nonamed.nonamedgame.Config;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import static com.nonamed.nonamedgame.Config.NAME_LIST;
import static com.nonamed.nonamedgame.Config.RANDOM;
import static com.nonamed.nonamedgame.StaticData.SVINO_PES_ANIMATED;
import static com.nonamed.nonamedgame.scenes.GameWorldHandler.HERO;

public class DarkPerson extends AbstractPerson {

    public static final int DEFAULT_HEALTH_FOR_DARK_SIDE = 1000;
    public static final int DEFAULT_DAMAGE_FOR_DARK_SIDE = 3;

    public DarkPerson(int posX, int posY) {
        name = NAME_LIST.get(RANDOM.nextInt(NAME_LIST.size()));
        this.health = DEFAULT_HEALTH_FOR_DARK_SIDE;
        this.damage = DEFAULT_DAMAGE_FOR_DARK_SIDE;
        this.posX = posX;
        this.posY = posY;
        speed = 2;

        setUpPersonPicturesGroup();
//        initTimerToMovePerson();
    }

    @Override
    protected void setUpPersonPicturesGroup() {
        imageView = new ImageView(SVINO_PES_ANIMATED);
        group.getChildren().add(imageView);
        initNameText();
        initHealthLine(DEFAULT_HEALTH_FOR_DARK_SIDE, Color.RED);
    }

    public void move() {
        moveToTarget(HERO.getPosX(), HERO.getPosY());
        if (group.getBoundsInParent().intersects(HERO.getGroup().getBoundsInParent())) {
            health -= HERO.getDamage();
            HERO.setHealth(HERO.getHealth() - damage);
            if (posX - HERO.getPosX() > 0) {
                HERO.fightRight = true;
            } else {
                HERO.fightLeft = true;
            }
            if (health < 0) {
                timer.stop();
            }

        }

    }

    protected void upDatePerson() {
        speed = Config.RANDOM.nextInt(Config.DARK_PERSON_SPEED) - 2;
        upDateAllPersonImages(DEFAULT_HEALTH_FOR_DARK_SIDE);
    }

    public void moveToTarget(int targetPosX, int targetPosY) {
        if (posX < targetPosX) {
            setPosX(getPosX() - speed);
        } else {
            setPosX(getPosX() + speed);
        }
        upDatePerson();
        if (posY < targetPosY) {
            setPosY(getPosY() - speed);
            group.setLayoutY(getPosY());
        } else {
            setPosY(getPosY() + speed);
        }
        upDatePerson();
    }

}