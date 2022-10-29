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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.nonamed.nonamedgame.App.HERO;
import static com.nonamed.nonamedgame.Config.NAME_LIST;
import static com.nonamed.nonamedgame.Config.RANDOM;
import static com.nonamed.nonamedgame.utils.HeroSoundService.sayIfDamaged;

public class Enemy {
    private final String name;
    private Text nameText;
    private int health;
    private final int damage;
    private int speed;
    private final Image image;

    private final ImageView imageView;
    private int posX;
    private int posY;

    private final AnimationTimer timerEnemyMove;
    private final double lineHealthLambda;

    private final Rectangle enemyBodyCollisionRectangle;

    private boolean isFight = false;

    private final Line healthLine;
    private final Group groupEnemy;

    private final Circle miniMapPoint;

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

    public Enemy() {
        groupEnemy = new Group();
        this.name = Config.ENEMY_NAME;
        this.health = Config.ENEMY_HEALTH;
        this.damage = Config.ENEMY_DAMAGE;
        this.speed = Config.ENEMY_SPEED;

        this.posX = (int) (1000+ ThreadLocalRandom.current().nextInt(-800, 2500));
        this.posY = (int) (1000+ ThreadLocalRandom.current().nextInt(-600, 2000));


        this.image = StaticData.SVINO_PES_3_IMAGE;
        this.imageView = new ImageView(image);
        groupEnemy.setLayoutX(posX);
        groupEnemy.setLayoutY(posY);
        this.imageView.setX(0);
        this.imageView.setY(0);



        this.lineHealthLambda = 90 / (double) (Config.ENEMY_HEALTH / Config.HERO_DAMAGE);

        this.healthLine = new Line();


        healthLine.setStartX(0 + 20);
        healthLine.setStartY(0 - 5);
        healthLine.setEndX(0 + 110);
        healthLine.setEndY(0 - 5);
        healthLine.setStrokeWidth(6);
        healthLine.setStroke(Color.RED);

        enemyBodyCollisionRectangle = new Rectangle();
        enemyBodyCollisionRectangle.setX(0 + 40);
        enemyBodyCollisionRectangle.setY(0 + 30);
        enemyBodyCollisionRectangle.setWidth(50);
        enemyBodyCollisionRectangle.setHeight(90);
        enemyBodyCollisionRectangle.setFill(Color.RED);
        enemyBodyCollisionRectangle.setOpacity(0);

        miniMapPoint = new Circle();
        miniMapPoint.setCenterX(200);
        miniMapPoint.setCenterY(40);
        miniMapPoint.setRadius(5);
        miniMapPoint.setFill(Color.BLACK);

        initNameText();


        groupEnemy.getChildren().addAll(imageView, healthLine, enemyBodyCollisionRectangle, nameText);
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

    protected void initNameText() {
        nameText = new Text(NAME_LIST.get(RANDOM.nextInt(NAME_LIST.size())));
        nameText.setX(0);
        nameText.setY(15);
//        nameText.setWrappingWidth(256);
        nameText.setTextAlignment(TextAlignment.CENTER);
        nameText.setFont(Font.font(12));
    }

    public void collisionWithHero(){
        if (App.HERO.getBodyCollision().getBoundsInParent().intersects(groupEnemy.getBoundsInParent())){
            App.HERO.damageFromEnemy();
        }
        if (App.HERO.isRightKick() && !isFight){
            if (App.HERO.getRightKickCollision().getBoundsInParent().intersects(groupEnemy.getBoundsInParent())){
                damageFromHero();
            }
        } if (App.HERO.isLeftKick() && !isFight) {
            if (App.HERO.getLeftKickCollision().getBoundsInParent().intersects(groupEnemy.getBoundsInParent())){
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

    public void damageFromHero() {
        if(HERO.isDamageAction){
            sayIfDamaged();
            setHealth(this.health - Config.HERO_DAMAGE);
            healthLine.setEndX(healthLine.getEndX() - lineHealthLambda);
            if (this.health <= 0) {
                HeroSoundService.say();
                App.gamePane.getChildren().remove(groupEnemy);
                App.gameWorld.getMiniMap().getChildren().remove(miniMapPoint);
                timerEnemyMove.stop();
            }
        }
    }

    public void moveToTarget(int targetPosX, int targetPosY) {
        speed = new Random().nextInt(Config.DARK_PERSON_SPEED);

        if (groupEnemy.getLayoutX() < targetPosX) {
            groupEnemy.setLayoutX(groupEnemy.getLayoutX() + speed);
//            healthLine.setStartX(healthLine.getStartX() + speed);
//            healthLine.setEndX(healthLine.getEndX() + speed);
//            enemyBodyCollisionRectangle.setX(enemyBodyCollisionRectangle.getX() + speed);
            posX += speed;
            calculateAndUpdateMiniMapPoint();
        } else {
            groupEnemy.setLayoutX(groupEnemy.getLayoutX() - speed);
//            healthLine.setStartX(healthLine.getStartX() - speed);
//            healthLine.setEndX(healthLine.getEndX() - speed);
//            enemyBodyCollisionRectangle.setX(enemyBodyCollisionRectangle.getX() - speed);
            posX -= speed;
            calculateAndUpdateMiniMapPoint();
        }
        if (groupEnemy.getLayoutY() < targetPosY) {
            groupEnemy.setLayoutY(groupEnemy.getLayoutY() + speed);
//            healthLine.setStartY(healthLine.getStartY() + speed);
//            healthLine.setEndY(healthLine.getEndY() + speed);
//            enemyBodyCollisionRectangle.setY(enemyBodyCollisionRectangle.getY() + speed);
            posY += speed;
            calculateAndUpdateMiniMapPoint();
        } else {
//            imageView.setY(imageView.getY() - speed);
            groupEnemy.setLayoutY(groupEnemy.getLayoutY() - speed);
//            healthLine.setStartY(healthLine.getStartY() - speed);
//            healthLine.setEndY(healthLine.getEndY() - speed);
//            enemyBodyCollisionRectangle.setY(enemyBodyCollisionRectangle.getY() - speed);
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
