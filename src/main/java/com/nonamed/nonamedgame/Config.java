package com.nonamed.nonamedgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Config {

    public static int HERO_SPEED = 5;
    public static int HERO_DAMAGE = 5;
    public static int HERO_HEALTH = 10000;
    public static int HERO_ENEMY_GOAL = 7;
    public static int HERO_KEY_GOAL = 4;
    public static boolean IS_KEY_GOAL = false;
    public static int DARK_PERSON_SPEED = 3;
    public static int VOLUME = 80;

    public static final Random RANDOM = new Random();

    public static List<String> NAME_LIST = new ArrayList<>();

    static {
        NAME_LIST.add("Vlad");
        NAME_LIST.add("Yana");
        NAME_LIST.add("Vitalik");
        NAME_LIST.add("Ivan");
        NAME_LIST.add("Artem");
    }

}
