module com.nonamed.nonamedgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.nonamed.nonamedgame to javafx.fxml;
    exports com.nonamed.nonamedgame;
}