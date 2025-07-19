module com.ndnt.apppractice {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.ndnt.apppractice to javafx.fxml;
    exports com.ndnt.apppractice;
    exports com.ndnt.pojo;
}
