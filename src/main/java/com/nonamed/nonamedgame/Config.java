package com.nonamed.nonamedgame;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.nonamed.nonamedgame.StaticData.*;

public final class Config {

    public static boolean isUsik = true;

    public static Image HERO_ANIMATED;
    public static Image HERO_FIGHT_LEFT;
    public static Image HERO_FIGHT_RIGHT;
    public static Image HERO_AVATAR;

    public static void setUpProfileConfig(){
        if(isUsik){
            HERO_NAME_STR = "УСИК";
            HERO_ANIMATED = USIK_ANIMATED;
            HERO_FIGHT_LEFT = USIK_FIGHT_LEFT;
            HERO_FIGHT_RIGHT = USIK_FIGHT_RIGHT ;
            HERO_AVATAR = USIK_AVATAR;
        } else {
            HERO_ANIMATED = OLHA_ANIMATED;
            HERO_FIGHT_LEFT = OLHA_FIGHT_LEFT;
            HERO_FIGHT_RIGHT = OLHA_FIGHT_RIGHT ;
            HERO_AVATAR = OLHA_AVATAR;
            HERO_NAME_STR = "ХАРЛАН";
        }
    }

    public static final Random RANDOM = new Random();

    public static double VOLUME_LEVEL = 0.5;

    public static int WINDOW_HEIGHT = 1080;
    public static int WINDOW_WIDTH = 1920;
    public static int WORLD_HEIGHT = 2560;
    public static int WORLD_WIDTH = 5120;
    public static String HERO_NAME_STR = "";
    public static int HERO_SPEED = 5;
    public static int HERO_DAMAGE = 5;
    public static int HERO_HEALTH = 10000;
    public static int HERO_ENERGY = 10000;

    public static int DARK_PERSON_SPEED = 3;
    public static int ENEMY_DAMAGE = 3;
    public static List<String> NAME_LIST = new ArrayList<>();



    public static String RIGHT_HERO_KICK = "X";
    public static String ENEMY_NAME = "!*!";
    public static int ENEMY_HEALTH = 3000;
    public static int ENEMY_SPEED = 3;


    static {
        NAME_LIST.add("ВЛАД");
        NAME_LIST.add("ЯНА");
        NAME_LIST.add("ВІТАЛІК");
        NAME_LIST.add("ІВАН");
        NAME_LIST.add("АРТЕМ");
        NAME_LIST.add("АЛЬТАЇР ІБН ЛА-АХАД");
        NAME_LIST.add("АМАНДА РІПЛІ");
        NAME_LIST.add("БАГЗ БАННІ");
        NAME_LIST.add("БАКІ БАРНС");
        NAME_LIST.add("БАСТИЛА ШАН");
        NAME_LIST.add("БЕТВУМЕН");
        NAME_LIST.add("БОЯГУЗ");
        NAME_LIST.add("БАЛБЕС");
        NAME_LIST.add("БУВАЛИЙ");
        NAME_LIST.add("ВАЛУЇДЖІ");
        NAME_LIST.add("ГЕКТОР БАРБОССА");
        NAME_LIST.add("ГОРО (MORTAL KOMBAT)");
        NAME_LIST.add("ГУМБА");
        NAME_LIST.add("ГУФІ");
        NAME_LIST.add("ГЮНТЕР О'ДІММ");
        NAME_LIST.add("ҐАЛЕН МАРЕК");
        NAME_LIST.add("ДАРТ МАЛАК");
        NAME_LIST.add("ДАРТ НІГІЛЮС");
        NAME_LIST.add("ДАРТ РЕВАН");
        NAME_LIST.add("ДАРТ СІОН");
        NAME_LIST.add("ДЖАРЕК");
        NAME_LIST.add("ДЖЕК СПАРРОУ");
        NAME_LIST.add("ДЖЕКС");
        NAME_LIST.add("ДЖОН КОСТЯНТИН");
        NAME_LIST.add("ДЖОН МАККЛЕЙН");
        NAME_LIST.add("ДОНКІ КОНГ");
        NAME_LIST.add("ЕРМАК");
        NAME_LIST.add("ЕЦІО АУДІТОРЕ ДА ФІРЕНЦЕ");
        NAME_LIST.add("ЕШ КЕТЧУМ");
        NAME_LIST.add("ІНДІАНА ДЖОНС");
        NAME_LIST.add("ЙОШІ");
        NAME_LIST.add("КАБАЛ");
        NAME_LIST.add("ШАО КАН");
        NAME_LIST.add("КАПІТАН АМЕРИКА");
        NAME_LIST.add("КАПІТАН БРИТАНІЯ");
        NAME_LIST.add("КЕЙСІ КЕЙДЖ");
        NAME_LIST.add("КІТАНА");
        NAME_LIST.add("КОНАН-ВАРВАР");
        NAME_LIST.add("КРІПЕР");
        NAME_LIST.add("КРОССБОУНС");
        NAME_LIST.add("ЛАРА КРОФТ");
        NAME_LIST.add("ЛІНК");
        NAME_LIST.add("ЛУЇДЖІ");
        NAME_LIST.add("ЛЮ КАН");
        NAME_LIST.add("МАКС ПЕЙН");
        NAME_LIST.add("МАРІО");
        NAME_LIST.add("МІЛІНА");
        NAME_LIST.add("МУГЛ");
        NAME_LIST.add("НЕКРОМОРФИ");
        NAME_LIST.add("ПІРАМІДОГОЛОВИЙ");
        NAME_LIST.add("ПІТЕР ПЕН");
        NAME_LIST.add("ПРИНЦЕСА ДЕЙЗІ");
        NAME_LIST.add("ПРИНЦЕСА ПІЧ");
        NAME_LIST.add("ПРИНЦЕСА РОЗАЛІНА");
        NAME_LIST.add("РАЙДЕН");
        NAME_LIST.add("РЕЙН");
        NAME_LIST.add("ДЖОН РЕМБО");
        NAME_LIST.add("РІДДІК");
        NAME_LIST.add("РОБІН ГУД");
        NAME_LIST.add("РОБОКОП");
        NAME_LIST.add("РОЗАЛІНД ЛЮТЕС");
        NAME_LIST.add("РОРШАХ");
        NAME_LIST.add("САБ-ЗІРО");
        NAME_LIST.add("СКАРЛЕТ");
        NAME_LIST.add("СКОРПІОН");
        NAME_LIST.add("СУДЖІНКО");
        NAME_LIST.add("ТИРАН");
        NAME_LIST.add("ТІМ ДРЕЙК");
        NAME_LIST.add("ТОАД");
        NAME_LIST.add("ФЛЕМЕТ");
        NAME_LIST.add("ФУДЖІН");
        NAME_LIST.add("ХАГГІ ВАГГІ");
        NAME_LIST.add("ХИЖАК");
        NAME_LIST.add("ЧОКОБО");
        NAME_LIST.add("ЧУЖИЙ");
        NAME_LIST.add("ШАНГ ЦУНГ");
        NAME_LIST.add("ШІННОК");
        NAME_LIST.add("ЯНГОЛ");
        NAME_LIST.add("DOOMGUY");
        NAME_LIST.add("TUX");
    }

}

