package com.nonamed.nonamedgame;

import com.nonamed.nonamedgame.game_objects.Hud;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import static com.nonamed.nonamedgame.App.*;

@Getter
@Setter
public class Hero {

    public static final String WAIT_STATUS = "wait";
    public static final String RIGHT = "Right";
    public static final String LEFT = "Left";

    public static final int KICK_ANIMATION_TIME = 700;
    public boolean isDamageAction = false;
    private int energy;
    private final double lineHealthLambda;
    private final int damage;
    private final int speed;
    private final Image image;
    private final ImageView imageView;
    private final Group heroGroup;
    private final Rectangle rightKickCollision;
    private final Rectangle leftKickCollision;
    private final Rectangle bodyCollision;
    private final AnimationTimer timerHeroMove;
    private final Circle miniMapPoint;
    private String name;
    private int health;
    private int posX;
    private int posY;
    private boolean isKick = false;
    private boolean isRightKick = false;
    private boolean isLeftKick = false;
    private boolean isUP;
    private boolean isDOWN;
    private boolean isRIGHT;
    private boolean isLEFT;
    private String moveStatus;

    public Hero() {
        this.name = Config.HERO_NAME;
        this.health = Config.HERO_HEALTH;
        this.damage = Config.HERO_DAMAGE;
        this.speed = Config.HERO_SPEED;
        this.energy = Config.HERO_ENERGY;

        this.image = Config.HERO_ANIMATED;
        this.imageView = new ImageView(image);
        this.imageView.setDisable(true);
        this.posX = 1500;
        this.posY = 1400;
        this.imageView.setX(500);
        this.imageView.setY(400);
        this.lineHealthLambda = 600 / (double) (Config.HERO_HEALTH / Config.ENEMY_DAMAGE);

        rightKickCollision = new Rectangle();
        rightKickCollision.setX(imageView.getX() + 80);
        rightKickCollision.setY(imageView.getY() + 50);
        rightKickCollision.setWidth(64);
        rightKickCollision.setHeight(64);
        rightKickCollision.setFill(Color.RED);
        rightKickCollision.setOpacity(0);

        leftKickCollision = new Rectangle();
        leftKickCollision.setX(imageView.getX() - 10);
        leftKickCollision.setY(imageView.getY() + 50);
        leftKickCollision.setWidth(64);
        leftKickCollision.setHeight(64);
        leftKickCollision.setFill(Color.RED);
        leftKickCollision.setOpacity(0);

        bodyCollision = new Rectangle();
        bodyCollision.setX(imageView.getX() + 40);
        bodyCollision.setY(imageView.getY() + 50);
        bodyCollision.setWidth(50);
        bodyCollision.setHeight(50);
        bodyCollision.setFill(Color.RED);
        bodyCollision.setOpacity(0);

        miniMapPoint = new Circle();
        miniMapPoint.setCenterX(200);
        miniMapPoint.setCenterY(40);
        miniMapPoint.setRadius(5);
        miniMapPoint.setFill(Color.GREEN);


        setUpKeyHandlerActions(App.getStage());

        timerHeroMove = new AnimationTimer() {
            @Override
            public void handle(long l) {
                move();
            }
        };

        heroGroup = new Group();
        heroGroup.getChildren().addAll(rightKickCollision, leftKickCollision, bodyCollision, imageView);

        timerHeroMove.start();
        App.gamePane.getChildren().add(heroGroup);
        App.gameWorld.getMiniMap().getChildren().add(miniMapPoint);
    }

    public AnimationTimer getTimerHeroMove() {
        return timerHeroMove;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Circle getMiniMapPoint() {
        return miniMapPoint;
    }

    public boolean isRightKick() {
        return isRightKick;
    }

    public boolean isLeftKick() {
        return isLeftKick;
    }

    public Rectangle getRightKickCollision() {
        return rightKickCollision;
    }

    public Rectangle getLeftKickCollision() {
        return leftKickCollision;
    }

    public Rectangle getBodyCollision() {
        return bodyCollision;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setUpKeyHandlerActions(Stage scene) {
        onKeyPressed(scene);
        onKeyReleased(scene);
    }

    private void onKeyReleased(Stage scene) {
        scene.addEventFilter(KeyEvent.KEY_RELEASED, (keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP, W -> isUP = false;
                case DOWN, S -> isDOWN = false;
                case RIGHT, D -> isRIGHT = false;
                case LEFT, A -> isLEFT = false;
            }
            moveStatus = WAIT_STATUS;
        }));
    }

    private void onKeyPressed(Stage scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP, W -> isUP = true;
                case DOWN, S -> isDOWN = true;
                case RIGHT, D -> isRIGHT = true;
                case LEFT, A -> isLEFT = true;
                case X, E -> heroKickAction(RIGHT);
                case Z, Q -> heroKickAction(LEFT);
            }
        }));
    }

    private void heroKickAction(String kickSide) {
        if (!isKick) {
            if (kickSide.equals(RIGHT)) {
                imageView.setImage(Config.HERO_FIGHT_RIGHT);
                isRightKick = true;
            } else if (kickSide.equals(LEFT)) {
                imageView.setImage(Config.HERO_FIGHT_LEFT);
                isLeftKick = true;
            }
            isKick = true;
            Thread kick = new Thread(() -> {
                try {
                    Thread.sleep(KICK_ANIMATION_TIME);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImage(Config.HERO_ANIMATED);
                        isKick = false;
                        isRightKick = false;
                        isLeftKick = false;
                    }
                });
            });
            kick.start();
        }
    }

    public void damageFromEnemy() {
        setHealth(HERO.getHealth() - Config.ENEMY_DAMAGE);
        isDamageAction = true;
        if(getHealth() < 0){
            App.stopGame();
            MEDIA_PLAYER.stop();
            MEDIA_PLAYER = new MediaPlayer(StaticData.GAME_OVER_SOUND);
            MEDIA_PLAYER.play();
            App.getStage().setScene(mainMenuPane.getScene());
            // FIXME set result window
        }
//        if (App.gameWorld.getHeroHealthLine().getEndX() < 768) {
//            App.setStageScene(App.resultMenuPane.getScene());
//        }

//        App.gameWorld.getHeroHealthLine().setEndX(App.gameWorld.getHeroHealthLine().getEndX() - lineHealthLambda);
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


    public void move() {
        setEnergy(getEnergy() - 1);
        Hud.updateGroup();
        if (isUP) {
            if (this.imageView.getY() < 230) {
                if (App.gameWorld.getBackgroundStaticImage().getY() < -10) {
                    App.gameWorld.getBackgroundStaticImage().setY(App.gameWorld.getBackgroundStaticImage().getY() + speed);
                    posY -= speed;
                    calculateAndUpdateMiniMapPoint();
                } else if (this.imageView.getY() > 10) {
                    imageView.setY(imageView.getY() - speed);
                    posY -= speed;
                    rightKickCollision.setY(rightKickCollision.getY() - speed);
                    leftKickCollision.setY(leftKickCollision.getY() - speed);
                    bodyCollision.setY(bodyCollision.getY() - speed);
                    calculateAndUpdateMiniMapPoint();
                }
            } else {
                imageView.setY(imageView.getY() - speed);
                posY -= speed;
                rightKickCollision.setY(rightKickCollision.getY() - speed);
                leftKickCollision.setY(leftKickCollision.getY() - speed);
                bodyCollision.setY(bodyCollision.getY() - speed);
                calculateAndUpdateMiniMapPoint();
            }
        }
        if (isDOWN) {
            if (this.imageView.getY() > KICK_ANIMATION_TIME) {
                if (App.gameWorld.getBackgroundStaticImage().getY() > -2000) {
                    App.gameWorld.getBackgroundStaticImage().setY(App.gameWorld.getBackgroundStaticImage().getY() - speed);
                    posY += speed;
                    calculateAndUpdateMiniMapPoint();
                } else if (this.imageView.getY() < 800) {
                    imageView.setY(imageView.getY() + speed);
                    posY += speed;
                    rightKickCollision.setY(rightKickCollision.getY() + speed);
                    leftKickCollision.setY(leftKickCollision.getY() + speed);
                    bodyCollision.setY(bodyCollision.getY() + speed);
                    calculateAndUpdateMiniMapPoint();
                }
            } else {
                imageView.setY(imageView.getY() + speed);
                posY += speed;
                rightKickCollision.setY(rightKickCollision.getY() + speed);
                leftKickCollision.setY(leftKickCollision.getY() + speed);
                bodyCollision.setY(bodyCollision.getY() + speed);
                calculateAndUpdateMiniMapPoint();
            }
        }
        if (isRIGHT) {
            if (this.imageView.getX() > 1600) {
                if (App.gameWorld.getBackgroundStaticImage().getX() > -2000) {
                    App.gameWorld.getBackgroundStaticImage().setX(App.gameWorld.getBackgroundStaticImage().getX() - speed);
                    posX += speed;
                    calculateAndUpdateMiniMapPoint();
                } else if (this.imageView.getX() < 1800) {
                    imageView.setX(imageView.getX() + speed);
                    posX += speed;
                    rightKickCollision.setX(rightKickCollision.getX() + speed);
                    leftKickCollision.setX(leftKickCollision.getX() + speed);
                    bodyCollision.setX(bodyCollision.getX() + speed);
                    calculateAndUpdateMiniMapPoint();
                }

            } else {
                imageView.setX(imageView.getX() + speed);
                posX += speed;
                rightKickCollision.setX(rightKickCollision.getX() + speed);
                leftKickCollision.setX(leftKickCollision.getX() + speed);
                bodyCollision.setX(bodyCollision.getX() + speed);
                calculateAndUpdateMiniMapPoint();
            }
        }
        if (isLEFT) {
            if (this.imageView.getX() < 230) {
                if (App.gameWorld.getBackgroundStaticImage().getX() < -10) {
                    App.gameWorld.getBackgroundStaticImage().setX(App.gameWorld.getBackgroundStaticImage().getX() + speed);
                    posX -= speed;
                    calculateAndUpdateMiniMapPoint();
                } else if (this.imageView.getX() > 10) {
                    imageView.setX(imageView.getX() - speed);
                    posX -= speed;
                    rightKickCollision.setX(rightKickCollision.getX() - speed);
                    leftKickCollision.setX(leftKickCollision.getX() - speed);
                    bodyCollision.setX(bodyCollision.getX() - speed);
                    calculateAndUpdateMiniMapPoint();
                }
            } else {
                imageView.setX(imageView.getX() - speed);
                posX -= speed;
                rightKickCollision.setX(rightKickCollision.getX() - speed);
                leftKickCollision.setX(leftKickCollision.getX() - speed);
                bodyCollision.setX(bodyCollision.getX() - speed);
                calculateAndUpdateMiniMapPoint();
            }
        }
    }
}

