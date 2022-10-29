package com.nonamed.nonamedgame;

//import javafx.embed.swing.SwingFXUtils;

import com.nonamed.nonamedgame.game_objects.Hud;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import static com.nonamed.nonamedgame.StaticData.BACKGROUND_IMAGE;

public class GameWorld {

    private final ImageView backgroundStaticImage;

    public static Pane miniMap;

    public GameWorld() {
        backgroundStaticImage = new ImageView(BACKGROUND_IMAGE);
        backgroundStaticImage.setX(-1000);
        backgroundStaticImage.setY(-1000);


        App.gamePane.getChildren().add(backgroundStaticImage);

        App.gamePane.getChildren().add(Hud.generateHudGroup());
//        App.gamePane.getChildren().add(miniMap);

    }


    static  {
        miniMap = new Pane();
        miniMap.setLayoutX(27);
        miniMap.setLayoutY(784);
        miniMap.setPrefWidth(512);
        miniMap.setPrefHeight(256);
        miniMap.setStyle("-fx-background-color: BISQUE");
    }

    public ImageView getBackgroundStaticImage() {
        return backgroundStaticImage;
    }


    public Pane getMiniMap() {
        return miniMap;
    }

//    public void updateHudGroup(String direction){
//        if (direction.equals("UP"))
//            hudGroup.setLayoutY(hudGroup.getLayoutY() - Config.HERO_SPEED);
//    }

//    public void saveAsPng(Node node, String fname) {
//        saveAsPng(node, fname, new SnapshotParameters());
//    }

//    public void saveAsPng(Node node, String fname, SnapshotParameters ssp) {
//        WritableImage image = node.snapshot(ssp, null);
//        File file = new File(fname+".png");
//        try {
//            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
//        } catch (IOException e) {
//            System.out.println("Помилка збереження результату у форматі <png>");
//        }
//    }
}
