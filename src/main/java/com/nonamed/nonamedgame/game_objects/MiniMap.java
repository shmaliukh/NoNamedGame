package com.nonamed.nonamedgame.game_objects;

import com.nonamed.nonamedgame.App;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.nonamed.nonamedgame.scenes.GameWorldHandler.gameWorld;

public final class MiniMap {

    public static final double scaleFactor = 0.1;

    public static Group mapGroup = new Group();
    public static ImageView miniMapImageView = new ImageView();

    static Rectangle rectangle = new Rectangle();

    static Rectangle generateRect(double x, double y) {
        Rectangle rectangle = new Rectangle();
        rectangle.setLayoutX(x);
        rectangle.setLayoutY(y);
        rectangle.setHeight(13);
        rectangle.setWidth(13);
        rectangle.setFill(Color.BLACK);
        gameWorld.getGamePane().getChildren().add(rectangle);
        return rectangle;
    }

    public static void upDateMiniMap() {
//        WritableImage snapshot = App.stage.getScene().snapshot(null);
//        miniMapImageView = new ImageView(snapshot);
//        miniMapImageView.setFitHeight(256);
//        miniMapImageView.setFitWidth(512);
////        personArrayList.forEach(o -> generateRect(o.getPosX(), o.getPosY())));
////        System.out.println("upDateMiniMap");
////        mapGroup.setScaleX(gameWorld.getGamePane().getWidth() * scaleFactor);
////        mapGroup.setScaleY(gameWorld.getGamePane().getHeight() * scaleFactor);
//        miniMapImageView.setLayoutX(32);
//        miniMapImageView.setLayoutY(892);

    }


}
