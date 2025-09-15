package com.ndnt.contacts;

import com.ndnt.Utils.FlyweightFactory;
import com.ndnt.Utils.MyAlert;
import com.ndnt.Utils.MyConfig;
import com.ndnt.pojo.Group;
import com.ndnt.pojo.User;
import com.ndnt.services.user.BaseUserServices;
import com.ndnt.services.user.GroupUserServicesDecorator;
import com.ndnt.services.user.KeywordUserServicesDecorator;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController implements Initializable {

    @FXML
    ComboBox<Group> cbGroups;
    @FXML
    TextField txtUser;
    @FXML
    TextField txtPhone;
    @FXML
    ComboBox<Group> cbGroupSearch;
    @FXML
    TextField txtSearch;
    @FXML
    TableView<User> tvUsers;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbGroups.setItems(FXCollections.observableArrayList(FlyweightFactory.getData(MyConfig.groupServices, "groups")));
            this.cbGroupSearch.setItems(FXCollections.observableArrayList(FlyweightFactory.getData(MyConfig.groupServices, "groups")));

            this.loadColumn();
            this.tvUsers.setItems(FXCollections.observableArrayList(MyConfig.userSerivces.list()));

        } catch (Exception ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.txtSearch.textProperty().addListener(e -> {
            try {
                BaseUserServices s = new KeywordUserServicesDecorator(MyConfig.userSerivces, this.txtSearch.getText());
                this.tvUsers.setItems(FXCollections.observableArrayList(s.list()));
            } catch (Exception ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        this.cbGroupSearch.getSelectionModel().selectedItemProperty().addListener(c -> {
            try {
                BaseUserServices s = new GroupUserServicesDecorator(MyConfig.userSerivces, this.cbGroupSearch.getSelectionModel().getSelectedItem().getId());
                this.tvUsers.setItems(FXCollections.observableArrayList(s.list()));
            } catch (Exception ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void addHandle(ActionEvent action) {
        try {
            User.Builder u = new User.Builder(txtUser.getText(), txtPhone.getText(), cbGroups.getSelectionModel().getSelectedItem());
            if (this.cbGroups.getSelectionModel().getSelectedItem().getId() == 1) {
                u.addImage("<3");
            }

            MyConfig.updateServices.addUser(u.build());
            MyAlert.getInstance().show("Thêm dữ liệu thành công!");
        } catch (SQLException ex) {
            MyAlert.getInstance().show("Thêm dữ liệu không thành công, lý do: " + ex.getMessage());
        } catch (Exception ex) {
            MyAlert.getInstance().show("Dữ liệu không hợp lệ !!!");
        }
    }

    private void loadColumn() {
        TableColumn colName = new TableColumn("Tên liên hệ");
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colName.setPrefWidth(200);

        TableColumn colPhone = new TableColumn("Số điện thoại");
        colPhone.setCellValueFactory(new PropertyValueFactory("phone"));
        colPhone.setPrefWidth(150);

        TableColumn colNote = new TableColumn("Ghi chú");
        colNote.setCellValueFactory(new PropertyValueFactory("image"));
        colNote.setPrefWidth(50);

        this.tvUsers.getColumns().addAll(colName, colPhone, colNote);
    }
}
