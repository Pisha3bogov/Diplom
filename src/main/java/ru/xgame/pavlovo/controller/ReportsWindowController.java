package ru.xgame.pavlovo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.xgame.pavlovo.model.Order;
import ru.xgame.pavlovo.service.OrderService;

import java.net.URL;
import java.sql.Time;
import java.util.Objects;
import java.util.ResourceBundle;

public class ReportsWindowController implements Initializable {
    public Label totalRevenue;
    public Label revenueOnPC;
    public Label revenueOnShop;
    public LineChart<Order, Time> graphickRevenue;

    private int totalRev = 0;

    private int revenuePc = 0;

    private int revenueShop = 0;

    ObservableList<Order> orders = FXCollections.observableArrayList();

    private final OrderService orderService;

    public ReportsWindowController() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        this.orderService = new OrderService(factory);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orders.addAll(orderService.findAll());

        for(Order order: orders) {
            totalRev += order.getCost();
            if (order.getName().equals("shop")) {
                revenueShop += order.getCost();
            } else if (order.getName().equals("PC")) {
                revenuePc += order.getCost();
            }
        }

        totalRevenue.setText(String.valueOf(totalRev));
        revenueOnPC.setText(String.valueOf(revenuePc));
        revenueOnShop.setText(String.valueOf(revenueShop));
    }

    public void endSession(ActionEvent actionEvent) {

    }
}
