package ru.xgame.pavlovo.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import ru.xgame.pavlovo.model.Order;
import ru.xgame.pavlovo.model.Reports;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
    public TableView<Order> tableView;
    public TableColumn<Order,String> nameProduct;
    public TableColumn<Order,Integer> sold;
    public TableColumn<Order,Integer> stock;
    public Label timeStart;
    public Label endTime;
    public Label timeSession;
    public Label profitOnPc;
    public Label profitOnShop;
    public Label allProfit;

    private int totalRev;

    private int revenueShop;

    private int revenuePc;

    ObservableList<Order> orders = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        orders.addAll(Context.getInstance().getOrders());

        tableView.setEditable(true);

        nameProduct.setCellValueFactory(u -> new SimpleObjectProperty<>(
                u.getValue().getShop() != null? u.getValue().getShop().getNameProduct(): null));
        sold.setCellValueFactory(u -> new SimpleObjectProperty<>(
                u.getValue().getShop() != null? u.getValue().getCount() : null));
        stock.setCellValueFactory(u -> new SimpleObjectProperty<>(
                u.getValue().getShop() != null?u.getValue().getShop().getCount():null));

        tableView.setItems(orders);

        Reports reports = Context.getInstance().getReports();

        timeStart.setText(String.valueOf(reports.getStartDate()));

        endTime.setText(String.valueOf(reports.getEndDate()));

        timeSession.setText(String.valueOf(reports.getTimeSession()));

        allProfit.setText(String.valueOf(reports.getProfit()));

        for (Order order : orders) {
            totalRev += order.getCost();
            if (order.getName().equals("shop")) {
                revenueShop += order.getCost();
            } else if (order.getName().equals("PC")) {
                revenuePc += order.getCost();
            }
        }

        profitOnPc.setText(String.valueOf(revenuePc));

        profitOnShop.setText(String.valueOf(revenueShop));

        Context.getInstance().setReports(null);

        Context.getInstance().setOrders(null);

    }

    @SneakyThrows
    public void endSession(ActionEvent actionEvent) {
        Button source = (Button) actionEvent.getSource();
        source.getScene().getWindow().hide();

        Stage stage = new Stage();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/startWindow.fxml")));

        stage.setTitle("Авторизация");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/image/x.jpg"))));
        stage.show();
    }
}
