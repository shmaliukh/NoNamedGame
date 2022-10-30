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

import static com.nonamed.nonamedgame.App_old.*;

@Getter
@Setter
public class Hero {

    public static final String GOOD_WAY = "good-way";
    public static final double m = 0;
    public static final String WAIT_STATUS = "wait";
    public static final String RIGHT = "Right";
    public static final String LEFT = "Left";
    public static final int KICK_ANIMATION_TIME = 700;
    public static int STEP_TO_AUTO_MOVE = 15;
    public static int SCORE = 0;
    static MediaPlayer loseMediaPlayer = new MediaPlayer(StaticData.GAME_OVER_SOUND);
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
    public boolean isDamageAction = false;
    boolean isStopped = false;
    boolean toLeft = false;
    boolean toRight = false;
    boolean toUp = false;
    boolean toDawn = false;
    private int energy;
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
        HERO.SCORE = 0;
        this.name = Config.HERO_NAME_STR;
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


        setUpKeyHandlerActions(App_old.getStage());

        timerHeroMove = new AnimationTimer() {
            @Override
            public void handle(long l) {
                move();
            }
        };

        heroGroup = new Group();
        heroGroup.getChildren().addAll(rightKickCollision, leftKickCollision, bodyCollision, imageView);

        timerHeroMove.start();
        App_old.gamePane.getChildren().add(heroGroup);
        App_old.gameWorld.getMiniMap().getChildren().add(miniMapPoint);
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
//        HeroSoundService.sayIfDamaged();

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
        if (getHealth() < 0) {
            ifLoseAction();
            // FIXME set result window
        }
//        if (App.gameWorld.getHeroHealthLine().getEndX() < 768) {
//            App.setStageScene(App.resultMenuPane.getScene());
//        }

//        App.gameWorld.getHeroHealthLine().setEndX(App.gameWorld.getHeroHealthLine().getEndX() - lineHealthLambda);
    }

    private void ifLoseAction() {
        App_old.stopGame();
        MEDIA_PLAYER.stop();
        App_old.getStage().setScene(mainMenuPane.getScene());
        loseMediaPlayer.play();
    }

    public void calculateAndUpdateMiniMapPoint() {
        int currentPosX = posX;
        int currentPosY = posY;
        int percentagePosX = currentPosX * 100 / 4500;
        int percentagePosY = currentPosY * 100 / 2500;
        int miniMapPosX = 512 * percentagePosX / 100;
        int miniMapPosY = 256 * percentagePosY / 100;

        getMiniMapPoint().setCenterX(miniMapPosX);
        getMiniMapPoint().setCenterY(miniMapPosY);
    }

    public void move() {
        MEDIA_PLAYER.setVolume(Config.VOLUME_LEVEL);
        setEnergy(getEnergy() - Config.HERO_ENERGY_USAGE);
        if (getEnergy() < 0) {
            ifLoseAction();
            // FIXME set result window
        }
        Hud.updateGroup();
//        autoMoveBack();


        GameWorldObjects collisionDetectWithHero = objectCollisionDetectWithHero();
        if (collisionDetectWithHero != null) {
            isStopped = true;
        } else if (isUP) {
            if (isStopped) {
                moveDown(speed + STEP_TO_AUTO_MOVE);
                isStopped = false;
            } else if (this.imageView.getY() < 230) {
                if (App_old.gameWorld.getBackgroundStaticImage().getY() < -10) {
                    App_old.gameWorld.getBackgroundStaticImage().setY(App_old.gameWorld.getBackgroundStaticImage().getY() + speed);
                    enemies.forEach(o -> {
                        o.moveDown(speed);
                    });
                    //App.gameWorld.getGameWorldPane().setLayoutY(App.gameWorld.getGameWorldPane().getLayoutY() + speed);
                    posY -= speed;
                    calculateAndUpdateMiniMapPoint();
                    App_old.updatePosition("UP");
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
//            }
        } else if (isDOWN) {
            if (isStopped) {
                moveUp(speed + STEP_TO_AUTO_MOVE);
                isStopped = false;
            } else
//            if (App.objectCollisionDetectWithHero().equals(GOOD_WAY) || isUP || isRIGHT || isLEFT){
                if (this.imageView.getY() > 650) {
                    if (App_old.gameWorld.getBackgroundStaticImage().getY() > -1800) {
                        App_old.gameWorld.getBackgroundStaticImage().setY(App_old.gameWorld.getBackgroundStaticImage().getY() - speed);
                        //App.gameWorld.getGameWorldPane().setLayoutY(App.gameWorld.getGameWorldPane().getLayoutY() - speed);
                        enemies.forEach(o -> o.moveUp(speed));
                        posY += speed;
                        calculateAndUpdateMiniMapPoint();
                        App_old.updatePosition("DOWN");
                    } else if (this.imageView.getY() < 650) {
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
//            }

        }
        if (isRIGHT) {
            if (isStopped) {
                moveLeft(speed + STEP_TO_AUTO_MOVE);
                isStopped = false;
            } else
//            if (App.objectCollisionDetectWithHero().equals(GOOD_WAY) || isDOWN || isUP || isLEFT) {
                if (this.imageView.getX() > 1600) {
                    if (App_old.gameWorld.getBackgroundStaticImage().getX() > -2700) {
                        App_old.gameWorld.getBackgroundStaticImage().setX(App_old.gameWorld.getBackgroundStaticImage().getX() - speed);
                        //App.gameWorld.getGameWorldPane().setLayoutX(App.gameWorld.getGameWorldPane().getLayoutX() - speed);
                        enemies.forEach(o -> o.moveLeft(speed));
                        posX += speed;
                        calculateAndUpdateMiniMapPoint();
                        App_old.updatePosition("RIGHT");
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
//            }

        } else if (isLEFT) {
            if (isStopped) {
                moveRight(speed + STEP_TO_AUTO_MOVE);
                isStopped = false;
            } else
//            if (App.objectCollisionDetectWithHero().equals(GOOD_WAY) || isDOWN || isUP || isRIGHT) {
                if (this.imageView.getX() < 230) {
                    if (App_old.gameWorld.getBackgroundStaticImage().getX() < -10) {
                        App_old.gameWorld.getBackgroundStaticImage().setX(App_old.gameWorld.getBackgroundStaticImage().getX() + speed);
                        //App.gameWorld.getGameWorldPane().setLayoutX(App.gameWorld.getGameWorldPane().getLayoutX() + speed);
                        enemies.forEach(o -> o.moveRight(speed));
                        posX -= speed;
                        calculateAndUpdateMiniMapPoint();
                        App_old.updatePosition("LEFT");
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

//    private void autoMoveBack() {
//        int stepToAutoMove = STEP_TO_AUTO_MOVE;
//        if ((HERO.getBodyCollision().contains(x1, y1))) {
//            System.out.println("x1y1");
//            moveLeft(stepToAutoMove);
//            moveUp(stepToAutoMove);
//            toUp = true;
//            toLeft = true;
//            x1 -= stepToAutoMove;
//            y1 -= stepToAutoMove;
//        }
//        if ((HERO.getBodyCollision().contains(x1, y2))) {
//            System.out.println("x1y2");
//            moveUp(stepToAutoMove);
//            moveRight(stepToAutoMove);
//            toUp = true;
//            toRight = true;
//            x1 += stepToAutoMove;
//            y2 -= stepToAutoMove;
//        }
//        if ((HERO.getBodyCollision().contains(x2, y2))) {
//            System.out.println("x2y2");
//            moveRight(stepToAutoMove);
//            moveDown(stepToAutoMove);
//            toDawn = true;
//            toRight = true;
//            x2 += stepToAutoMove;
//            y2 += stepToAutoMove;
//        }
//        if ((HERO.getBodyCollision().contains(x1, y2))) {
//            System.out.println("x1y2");
//            moveLeft(stepToAutoMove);
//            moveDown(stepToAutoMove);
//            toDawn = true;
//            toLeft = true;
//            x1 -= stepToAutoMove;
//            y2 += stepToAutoMove;
//        }
//    }

    private void moveRight(int stepToAutoMove) {
        imageView.setX(imageView.getX() + stepToAutoMove);
        posX += stepToAutoMove;
        rightKickCollision.setX(rightKickCollision.getX() + stepToAutoMove);
        leftKickCollision.setX(leftKickCollision.getX() + stepToAutoMove);
        bodyCollision.setX(bodyCollision.getX() + stepToAutoMove);
        calculateAndUpdateMiniMapPoint();
    }

    private void moveLeft(int stepToAutoMove) {
        imageView.setX(imageView.getX() - stepToAutoMove);
        posX -= stepToAutoMove;
        rightKickCollision.setX(rightKickCollision.getX() - stepToAutoMove);
        leftKickCollision.setX(leftKickCollision.getX() - stepToAutoMove);
        bodyCollision.setX(bodyCollision.getX() - stepToAutoMove);
        calculateAndUpdateMiniMapPoint();
    }

    private void moveDown(int stepToAutoMove) {
        imageView.setY(imageView.getY() + stepToAutoMove);
        posY += stepToAutoMove;
        rightKickCollision.setY(rightKickCollision.getY() + stepToAutoMove);
        leftKickCollision.setY(leftKickCollision.getY() + stepToAutoMove);
        bodyCollision.setY(bodyCollision.getY() + stepToAutoMove);
        calculateAndUpdateMiniMapPoint();
    }

    private void moveUp(int stepToAutoMove) {
        imageView.setY(imageView.getY() - stepToAutoMove);
        posY -= stepToAutoMove;
        rightKickCollision.setY(rightKickCollision.getY() - stepToAutoMove);
        leftKickCollision.setY(leftKickCollision.getY() - stepToAutoMove);
        bodyCollision.setY(bodyCollision.getY() - stepToAutoMove);
        calculateAndUpdateMiniMapPoint();
    }

}

