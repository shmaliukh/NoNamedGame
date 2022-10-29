package com.nonamed.nonamedgame.enemies;

import com.nonamed.nonamedgame.Config;
import com.nonamed.nonamedgame.Enemy;
import com.nonamed.nonamedgame.StaticData;
import javafx.scene.image.Image;

public class SvinoPesBuryat extends Enemy {

    public SvinoPesBuryat() {
        super();
    }

    protected void initMaxHealth() {
        maxHealth = (int) (Config.ENEMY_HEALTH * 0.5);
    }

    protected Image initAnimatedImage() {
        return StaticData.SVINO_PES_BURYAT;
    }

    protected int initDamage() {
        if(Config.ENEMY_DAMAGE > 6){
            return Config.ENEMY_DAMAGE + 3;
        }
        if(Config.ENEMY_DAMAGE > 3){
            return Config.ENEMY_DAMAGE + 2;
        }
        if(Config.ENEMY_DAMAGE > 2){
            return Config.ENEMY_DAMAGE + 1;
        }
        return Config.ENEMY_DAMAGE;
    }

    public int initSpeed() {
        return Config.ENEMY_SPEED + 2;
    }

}
