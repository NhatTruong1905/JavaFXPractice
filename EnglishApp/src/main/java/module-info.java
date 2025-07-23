module com.ndnt.englishapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.ndnt.englishapp to javafx.fxml;
    exports com.ndnt.englishapp;
    exports com.ndnt.pojo;
}
