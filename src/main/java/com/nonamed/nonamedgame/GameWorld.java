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

    private Line heroHealthLine;
    private Line heroEnergyLine;

    private Pane miniMap;

    public GameWorld() {
        backgroundStaticImage = new ImageView(BACKGROUND_IMAGE);
        backgroundStaticImage.setX(-1000);
        backgroundStaticImage.setY(-1000);

        initMap();

        heroEnergyLine = new Line();
        heroEnergyLine.setStrokeWidth(20);
        heroEnergyLine.setStartX(736);
        heroEnergyLine.setStartY(1050);
        heroEnergyLine.setEndX(1336);
        heroEnergyLine.setEndY(1050);
        heroEnergyLine.setStroke(Color.LIGHTYELLOW);

        App.gamePane.getChildren().add(backgroundStaticImage);

//        App.gamePane.getChildren().add(heroHealthLine);
//        App.gamePane.getChildren().add(heroEnergyLine);

        App.gamePane.getChildren().add(Hud.generateHudGroup());
        App.gamePane.getChildren().add(miniMap);

    }

    private void initHealthLine() {
        heroHealthLine = new Line();
        heroHealthLine.setStrokeWidth(20);
        heroHealthLine.setStartX(736);
        heroHealthLine.setStartY(1000);
        heroHealthLine.setEndX(1336);
        heroHealthLine.setEndY(1000);
        heroHealthLine.setStroke(Color.LIGHTGREEN);
    }

    private void initMap() {
        miniMap = new Pane();
        miniMap.setLayoutX(10);
        miniMap.setLayoutY(814);
        miniMap.setPrefWidth(512);
        miniMap.setPrefHeight(256);
        miniMap.setStyle("-fx-background-color: RED");
    }

    public ImageView getBackgroundStaticImage() {
        return backgroundStaticImage;
    }

    public Line getHeroHealthLine() {
        return heroHealthLine;
    }

    public Pane getMiniMap() {
        return miniMap;
    }

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
