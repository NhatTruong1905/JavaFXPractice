module com.ndnt.notelist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.ndnt.notelist to javafx.fxml;
    exports com.ndnt.notelist;
    exports com.ndnt.pojo;
}
