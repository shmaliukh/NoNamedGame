module com.nonamed.nonamedgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires org.slf4j;


    opens com.nonamed.nonamedgame to javafx.fxml;
    exports com.nonamed.nonamedgame;
    exports com.nonamed.nonamedgame.scenas;
    opens com.nonamed.nonamedgame.scenas to javafx.fxml;
}