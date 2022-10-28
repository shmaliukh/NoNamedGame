package com.nonamed.nonamedgame.game_objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseObject { // TODO rename

    protected Integer id = null;
    protected Image image;
    protected ImageView imageView;

    private int posX;
    private int posY;

    protected void initImageView(String imageFileNameStr) {
        initImageView(imageFileNameStr, this.posX, this.posY);
    }

    public void initImageView(String imageFileNameStr, int posX, int posY) {
        this.image = new Image(imageFileNameStr);
        imageView = new ImageView(image);
        imageView.setX(posX);
        imageView.setY(posY);
    }

}
