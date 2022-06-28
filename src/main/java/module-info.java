module com.example.videofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javacv;
    requires java.desktop;
    requires org.apache.commons.io;


    opens com.example.videofinal to javafx.fxml;
    exports com.example.videofinal;
}