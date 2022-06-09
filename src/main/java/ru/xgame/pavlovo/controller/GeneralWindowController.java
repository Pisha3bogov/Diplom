package ru.xgame.pavlovo.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.xgame.pavlovo.model.Hardware;
import ru.xgame.pavlovo.service.HardwareService;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class GeneralWindowController {

    public Label accountName;
    public ImageView imageAccount;
    public TableView<Hardware> tableView;
    public TableColumn<Hardware,Integer> numberColumn;
    public TableColumn<Hardware,String> typeColumn;
    public TableColumn<Hardware,String> statusColumn;
    public TableColumn<Hardware,String> userNameColumn;
    public TableColumn<Hardware,Date> userTimeSession;
    public TableColumn<Hardware,Date> userFinalSession;
    public TableColumn<Hardware,Integer> userBalance;
    public TableColumn<Hardware,Integer> userSale;

    private  final ObservableList<Hardware> hardwares = FXCollections.observableArrayList();

    private final HardwareService hardwareService;

    public GeneralWindowController() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        this.hardwareService = new HardwareService(factory);
    }

    @FXML
    public void initialize() {
        hardwares.addAll(hardwareService.findAll());

        imageAccount.setImage(new Image(String.valueOf(getClass().getResource("/image/x_variable2.jpg"))));
        imageAccount.setFitHeight(100);
        imageAccount.setFitWidth(100);

        accountName.setText(Context.getInstance().getAdmin().getLogin());

        tableView.setEditable(true);

        numberColumn.setCellValueFactory(u -> new SimpleObjectProperty<>(u.getValue().getIdHardware()));
        typeColumn.setCellValueFactory(u -> new SimpleObjectProperty<>(u.getValue().getType().getTypeName()));
        statusColumn.setCellValueFactory(u -> new SimpleObjectProperty<>(u.getValue().getStatus().getName()));
        userNameColumn.setCellValueFactory(u -> new SimpleObjectProperty<>(
                u.getValue().getUser()!=null?u.getValue().getUser().getName():null));
        userBalance.setCellValueFactory(u -> new SimpleObjectProperty<>(
                u.getValue().getUser()!=null?u.getValue().getUser().getBalance():null));
        userSale.setCellValueFactory(u -> new SimpleObjectProperty<>(
                u.getValue().getUser()!=null ? u.getValue().getUser().getSale().getSale():null));

        tableView.setItems(hardwares);

    }
}
