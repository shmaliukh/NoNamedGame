package com.nonamed.nonamedgame.enemies;

import com.nonamed.nonamedgame.Config;
import com.nonamed.nonamedgame.Enemy;
import com.nonamed.nonamedgame.StaticData;
import javafx.scene.image.Image;

public class SvinoPes extends Enemy {

    public SvinoPes() {
        super();
    }

    protected void initMaxHealth() {
        maxHealth = (int) (Config.ENEMY_HEALTH * 1.3);
    }

    protected Image initAnimatedImage() {
        return StaticData.SVINO_PES;
    }

    protected int initDamage() {
        return (int) (Config.ENEMY_DAMAGE +1);
    }

    public int initSpeed() {
        return Config.ENEMY_SPEED + 1;
    }

}
