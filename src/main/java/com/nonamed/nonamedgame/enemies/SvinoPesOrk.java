package com.nonamed.nonamedgame.enemies;

import com.nonamed.nonamedgame.Config;
import com.nonamed.nonamedgame.Enemy;
import com.nonamed.nonamedgame.StaticData;
import javafx.scene.image.Image;

public class SvinoPesOrk extends Enemy {

    public SvinoPesOrk() {
        super();
    }

    protected void initMaxHealth() {
        maxHealth = (int) (Config.ENEMY_HEALTH * 1.4);
    }

    protected Image initAnimatedImage() {
        return StaticData.SVINO_PES_ORK;
    }

    protected int initDamage() {
        return (int) (Config.ENEMY_DAMAGE);
    }

    public int initSpeed() {
        if(Config.ENEMY_SPEED > 6){
            return Config.ENEMY_SPEED - 3;
        }
        if(Config.ENEMY_SPEED > 3){
            return Config.ENEMY_SPEED - 2;
        }
        if(Config.ENEMY_SPEED > 2){
            return Config.ENEMY_SPEED - 1;
        }
        return Config.ENEMY_SPEED;
    }

}
