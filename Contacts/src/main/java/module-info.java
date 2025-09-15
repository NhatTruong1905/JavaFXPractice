module com.ndnt.contacts {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.ndnt.contacts to javafx.fxml;
    exports com.ndnt.contacts;
    exports com.ndnt.pojo;
}
