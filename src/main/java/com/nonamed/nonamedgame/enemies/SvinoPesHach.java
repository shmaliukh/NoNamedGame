package com.nonamed.nonamedgame.enemies;

import com.nonamed.nonamedgame.Config;
import com.nonamed.nonamedgame.Enemy;
import com.nonamed.nonamedgame.StaticData;
import javafx.scene.image.Image;

public class SvinoPesHach extends Enemy {

    public SvinoPesHach() {
        super();
    }

    protected void initMaxHealth() {
        maxHealth = (int) (Config.ENEMY_HEALTH * 0.8);
    }

    protected Image initAnimatedImage() {
        return StaticData.SVINO_PES_HATCH;
    }

    protected int initDamage() {
        if(Config.ENEMY_DAMAGE > 5){
            return Config.ENEMY_DAMAGE - 2;
        }
        if(Config.ENEMY_DAMAGE > 3){
            return Config.ENEMY_DAMAGE - 1;
        }
        return Config.ENEMY_DAMAGE;
    }

    public int initSpeed() {
        return Config.ENEMY_SPEED + 2;
    }

}
