package com.nonamed.nonamedgame.game_objects.micro;

import com.nonamed.nonamedgame.App;
import com.nonamed.nonamedgame.Config;
import com.nonamed.nonamedgame.scenes.LoseMenu;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static com.nonamed.nonamedgame.scenes.GameWorld.backgroundStaticImage;

public class Hero {
    private String name;
    private int health;
    private double lineHealthLambda;
    private int damage;
    private int speed;
    private Image image;
    private ImageView imageView;
    private int posX;
    private int posY;
    private Group heroGroup;
    private boolean isKick = false;
    private boolean isRightKick = false;
    private boolean isLeftKick = false;
    private Rectangle rightKickCollision;
    private Rectangle leftKickCollision;
    private Rectangle bodyCollision;
    private boolean isUP;
    private boolean isDOWN;
    private boolean isRIGHT;
    private boolean isLEFT;
    private AnimationTimer timerHeroMove;
    private Circle miniMapPoint;
    private String moveStatus;

    public Hero() {
        this.name = Config.HERO_NAME;
        this.health = Config.HERO_HEALTH;
        this.damage = Config.HERO_DAMAGE;
        this.speed = Config.HERO_SPEED;

        this.image = Config.HERO_ANIMATED;
        this.imageView = new ImageView(image);
        this.imageView.setDisable(true);
        this.posX = 1500;
        this.posY = 1400;
        this.imageView.setX(500);
        this.imageView.setY(400);
        this.lineHealthLambda = 512 / (double) (Config.HERO_HEALTH / Config.ENEMY_DAMAGE);

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


        setUpKeyHandlerActions(App.stage);

        timerHeroMove = new AnimationTimer() {
            @Override
            public void handle(long l) {
                move();
            }
        };

        heroGroup = new Group();
        heroGroup.getChildren().addAll(rightKickCollision, leftKickCollision, bodyCollision, imageView);

        timerHeroMove.start();
        App.gameWorld.getGamePane().getChildren().add(heroGroup);

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
                case UP -> isUP = false;
                case DOWN -> isDOWN = false;
                case RIGHT -> isRIGHT = false;
                case LEFT -> isLEFT = false;
            }
            moveStatus = "wait";
        }));
    }

    private void onKeyPressed(Stage scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (keyEvent -> {
            switch (keyEvent.getCode().toString()) {
                case "UP" -> isUP = true;
                case "DOWN" -> isDOWN = true;
                case "RIGHT" -> isRIGHT = true;
                case "LEFT" -> isLEFT = true;
                case "X" -> heroKickAction("Right");
                case "Z" -> heroKickAction("Left");
            }
        }));
    }

    private void heroKickAction(String kickSide) {
        if (!isKick) {
            if (kickSide.equals("Right")) {
                {
                    imageView.setImage(Config.HERO_FIGHT_RIGHT);
                    isRightKick = true;
                }

            } else if (kickSide.equals("Left")) {
                imageView.setImage(Config.HERO_FIGHT_LEFT);
                isLeftKick = true;
            }

            isKick = true;
            Thread kick = new Thread(() -> {
                try {
                    Thread.sleep(700);
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
        setHealth(App.HERO.getHealth() - Config.ENEMY_DAMAGE);
        if (App.HERO.getHealth() <= 0) {
            App.stage.setScene(LoseMenu.loseMenuScene);
        }
//  FIXME       App.gameWorld.getHeroHealthLine().setEndX(App.gameWorld.getHeroHealthLine().getEndX() - lineHealthLambda);
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
        if (isUP) {
            if (this.imageView.getY() < 230) {
                if (backgroundStaticImage.getY() < -10) {
                    backgroundStaticImage.setY(backgroundStaticImage.getY() + speed);
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
            if (this.imageView.getY() > 700) {
                if (backgroundStaticImage.getY() > -2000) {
                    backgroundStaticImage.setY(backgroundStaticImage.getY() - speed);
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
                if (backgroundStaticImage.getX() > -2000) {
                    backgroundStaticImage.setX(backgroundStaticImage.getX() - speed);
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
                if (backgroundStaticImage.getX() < -10) {
                    backgroundStaticImage.setX(backgroundStaticImage.getX() + speed);
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

