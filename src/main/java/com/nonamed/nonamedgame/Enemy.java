package com.nonamed.nonamedgame;

import com.nonamed.nonamedgame.utils.HeroSoundService;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.nonamed.nonamedgame.App.HERO;

public class Enemy {

    private final String name;
    private final int damage;
    private final Image image;
    private final ImageView imageView;
    private final AnimationTimer timerEnemyMove;
    private final double lineHealthLambda;
    private final Rectangle enemyBodyCollisionRectangle;
    private final Line healthLine;
    private final Group groupEnemy;
    private final Circle miniMapPoint;
    protected int maxHealth;
    private int health;
    private int speed;
    private int posX;
    private int posY;
    private boolean isFight = false;

    public Enemy() {
        initMaxHealth();
        this.name = Config.ENEMY_NAME;
        this.health = Config.ENEMY_HEALTH;
        this.damage = initDamage();
        this.speed = initSpeed();


        this.image = initAnimatedImage();
        this.imageView = new ImageView(image);
        this.imageView.setX(ThreadLocalRandom.current().nextInt(-800, 2500));
        this.imageView.setY(ThreadLocalRandom.current().nextInt(-600, 2000));

        this.posX = (int) (1000 + imageView.getX());
        this.posY = (int) (1000 + imageView.getY());

        this.lineHealthLambda = 90 / (double) (maxHealth / Config.HERO_DAMAGE);

        this.healthLine = new Line();
        healthLine.setStartX(this.imageView.getX() + 20);
        healthLine.setStartY(this.imageView.getY() - 5);
        healthLine.setEndX(this.imageView.getX() + 110);
        healthLine.setEndY(this.imageView.getY() - 5);
        healthLine.setStrokeWidth(5);
        healthLine.setStroke(Color.RED);

        enemyBodyCollisionRectangle = new Rectangle();
        enemyBodyCollisionRectangle.setX(imageView.getX() + 40);
        enemyBodyCollisionRectangle.setY(imageView.getY() + 30);
        enemyBodyCollisionRectangle.setWidth(50);
        enemyBodyCollisionRectangle.setHeight(90);
        enemyBodyCollisionRectangle.setFill(Color.RED);
        enemyBodyCollisionRectangle.setOpacity(0);

        miniMapPoint = new Circle();
        miniMapPoint.setCenterX(200);
        miniMapPoint.setCenterY(40);
        miniMapPoint.setRadius(5);
        miniMapPoint.setFill(Color.BLACK);

        groupEnemy = new Group();
        groupEnemy.getChildren().addAll(imageView, healthLine, enemyBodyCollisionRectangle);
        App.gameWorld.getMiniMap().getChildren().add(miniMapPoint);


        timerEnemyMove = new AnimationTimer() {
            @Override
            public void handle(long l) {
                moveToTarget((int) HERO.getImageView().getX(), (int) HERO.getImageView().getY());
                collisionWithHero();
            }
        };
        timerEnemyMove.start();
        App.gamePane.getChildren().add(groupEnemy);
    }

    public int initSpeed() {
        return Config.ENEMY_SPEED;
    }

    protected int initDamage() {
        return Config.ENEMY_DAMAGE;
    }

    public Circle getMiniMapPoint() {
        return miniMapPoint;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public AnimationTimer getTimerEnemyMove() {
        return timerEnemyMove;
    }

    protected Image initAnimatedImage() {
        return StaticData.SVINO_PES_3_IMAGE;
    }

    protected void initMaxHealth() {
        maxHealth = Config.ENEMY_HEALTH;
    }

    public void collisionWithHero(){
        if (App.HERO.getBodyCollision().getBoundsInParent().intersects(this.enemyBodyCollisionRectangle.getBoundsInParent())){
            App.HERO.damageFromEnemy();
        }
        if (App.HERO.isRightKick() && !isFight){
            if (App.HERO.getRightKickCollision().getBoundsInParent().intersects(this.enemyBodyCollisionRectangle.getBoundsInParent())){
                damageFromHero();
            }
        } if (App.HERO.isLeftKick() && !isFight) {
            if (App.HERO.getLeftKickCollision().getBoundsInParent().intersects(this.enemyBodyCollisionRectangle.getBoundsInParent())){
                damageFromHero();
            }
        }


        isFight = true;
        Thread kick = new Thread(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                }
            });
            isFight = false;

        });
        kick.start();


    }

    public void damageFromHero(){
        setHealth(this.health - Config.HERO_DAMAGE);
        healthLine.setEndX(healthLine.getEndX() - lineHealthLambda);
        if (this.health <= 0){
            HERO.SCORE += 10;
            App.gamePane.getChildren().remove(groupEnemy);
            App.gameWorld.getMiniMap().getChildren().remove(miniMapPoint);
            timerEnemyMove.stop();
        }

    }

    public void moveToTarget(int targetPosX, int targetPosY) {
        speed = new Random().nextInt(initSpeed());
        if (imageView.getX() < targetPosX) {
            imageView.setX(imageView.getX() + speed);
            healthLine.setStartX(healthLine.getStartX() + speed);
            healthLine.setEndX(healthLine.getEndX() + speed);
            enemyBodyCollisionRectangle.setX(enemyBodyCollisionRectangle.getX() + speed);
            posX += speed;
            calculateAndUpdateMiniMapPoint();
        } else {
            imageView.setX(imageView.getX() - speed);
            healthLine.setStartX(healthLine.getStartX() - speed);
            healthLine.setEndX(healthLine.getEndX() - speed);
            enemyBodyCollisionRectangle.setX(enemyBodyCollisionRectangle.getX() - speed);
            posX -= speed;
            calculateAndUpdateMiniMapPoint();
        }
        if (imageView.getY() < targetPosY) {
            imageView.setY(imageView.getY() + speed);
            healthLine.setStartY(healthLine.getStartY() + speed);
            healthLine.setEndY(healthLine.getEndY() + speed);
            enemyBodyCollisionRectangle.setY(enemyBodyCollisionRectangle.getY() + speed);
            posY += speed;
            calculateAndUpdateMiniMapPoint();
        } else {
            imageView.setY(imageView.getY() - speed);
            healthLine.setStartY(healthLine.getStartY() - speed);
            healthLine.setEndY(healthLine.getEndY() - speed);
            enemyBodyCollisionRectangle.setY(enemyBodyCollisionRectangle.getY() - speed);
            posY -= speed;
            calculateAndUpdateMiniMapPoint();
        }

    }

    public void calculateAndUpdateMiniMapPoint() {
        int currentPosX = posX;
        int currentPosY = posY;
        int percentagePosX = currentPosX * 100 / 4500;
        int percentagePosY = currentPosY * 100 / 3000;
        int miniMapPosX = 512 * percentagePosX / 100;
        int miniMapPosY = 256 * percentagePosY / 100;

        getMiniMapPoint().setCenterX(miniMapPosX);
        getMiniMapPoint().setCenterY(miniMapPosY);
    }

}
