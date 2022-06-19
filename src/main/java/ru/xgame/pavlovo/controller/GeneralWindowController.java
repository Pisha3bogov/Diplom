package ru.xgame.pavlovo.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.xgame.pavlovo.model.Hardware;
import ru.xgame.pavlovo.model.Reports;
import ru.xgame.pavlovo.service.HardwareService;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

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
    public Button createReport;

    public GeneralWindowController() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        this.hardwareService = new HardwareService(factory);
    }

    @FXML
    public void initialize() {
        hardwares.addAll(hardwareService.findAll());

        imageAccount.setImage(new Image(String.valueOf(getClass().getResource("/image/x_variable2.jpg"))));

        accountName.setText(Context.getInstance().getAdmin().getLogin());

        tableView.setEditable(true);

        numberColumn.setCellValueFactory(u -> new SimpleObjectProperty<>(u.getValue().getIdHardware()));
        typeColumn.setCellValueFactory(u -> new SimpleObjectProperty<>(u.getValue().getType().getTypeName()));
        statusColumn.setCellValueFactory(u -> new SimpleObjectProperty<>(u.getValue().getStatus().getName()));
        userNameColumn.setCellValueFactory(u -> new SimpleObjectProperty<>(
                u.getValue().getUser()!=null?u.getValue().getUser().getLogin():null));
        userBalance.setCellValueFactory(u -> new SimpleObjectProperty<>(
                u.getValue().getUser()!=null?u.getValue().getUser().getBalance():null));
        userSale.setCellValueFactory(u -> new SimpleObjectProperty<>(
                u.getValue().getUser()!=null ? u.getValue().getUser().getSale().getSale():null));

        tableView.setItems(hardwares);

        styleRowColor();

    }

    private void styleRowColor() {
        Callback<TableColumn<Hardware, String>, TableCell<Hardware, String>> cellFactory
                =
                new Callback<TableColumn<Hardware, String>, TableCell<Hardware, String>>() {
                    @Override
                    public TableCell<Hardware, String> call(final TableColumn<Hardware, String> param) {
                        final TableCell<Hardware, String> cell = new TableCell<Hardware, String>() {

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    setText(item);
                                    TableRow<Hardware> row = getTableRow();
                                    try {
                                        if (row.getItem().getStatus().getName().equals("Занят")) {
                                            row.getStyleClass().clear();
                                            setStyle("-fx-background-color: green");
                                        } else if (row.getItem().getStatus().getName().equals("Свободен")) {
                                            row.getStyleClass().clear();
                                            setStyle("-fx-background-color: yellow");
                                        }
                                    } catch (NullPointerException e) {
                                        System.out.println("Все в порядке, не парься");
                                    }
                                }
                            }
                        };
                        return cell;
                    }
                };
        statusColumn.setCellFactory(cellFactory);
    }

    @SneakyThrows
    public void openGeneralWindow(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(
                "/view/general_window.fxml")));
        stage.setTitle("Компьютеры");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/image/x.jpg"))));
        stage.show();

        closeScene(actionEvent);
    }

    @SneakyThrows
    public void openUsers(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(
                "/view/users_window.fxml")));
        stage.setTitle("Пользователи");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/image/x.jpg"))));
        stage.show();

        closeScene(actionEvent);
    }

    @SneakyThrows
    public void openShop(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(
                "/view/shop_windows.fxml")));
        stage.setTitle("Магазин");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/image/x.jpg"))));
        stage.show();

        closeScene(actionEvent);
    }

    @SneakyThrows
    public void openReports(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(
                "/view/reports_window.fxml")));
        stage.setTitle("Отчеты");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/image/x.jpg"))));
        stage.show();

        closeScene(actionEvent);
    }

    @SneakyThrows
    public void exitOnAccount(ActionEvent actionEvent) {
        closeScene(actionEvent);
    }

    private void closeScene(ActionEvent actionEvent){
        Button source = (Button) actionEvent.getSource();
        source.getScene().getWindow().hide();
    }

    public void createReport(ActionEvent actionEvent) {

        if(Context.getInstance().getReports() == null) {
            createReport.setDisable(true);

            Date date = new Date();
            Time timeDay = new Time(8, 0, 0);
            Time timeNight = new Time(20, 0, 0);

            Reports reports = new Reports(date);

            Context.getInstance().setReports(reports);

            createReport.setDisable(true);
        }
    }
}
