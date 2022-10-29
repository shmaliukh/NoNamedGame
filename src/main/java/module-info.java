module com.nonamed.nonamedgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires org.slf4j;
    requires javafx.media;


    opens com.nonamed.nonamedgame to javafx.fxml;
    exports com.nonamed.nonamedgame;
    exports com.nonamed.nonamedgame.scenes;
    opens com.nonamed.nonamedgame.scenes to javafx.fxml;
}