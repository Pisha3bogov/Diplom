package ru.xgame.pavlovo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.xgame.pavlovo.model.Order;
import ru.xgame.pavlovo.model.Reports;
import ru.xgame.pavlovo.service.ReportsService;

import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ReportsWindowController implements Initializable {
    public Label totalRevenue;
    public Label revenueOnPC;
    public Label revenueOnShop;
    public LineChart<Order, Time> graphickRevenue;
    public Label errorLabel;
    public CategoryAxis orderAxis;
    public NumberAxis numberAxis;

    private int totalRev = 0;

    private int revenuePc = 0;

    private int revenueShop = 0;

    private final ReportsService reportsService;



    public ReportsWindowController() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        this.reportsService = new ReportsService(factory);
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

        if(Context.getInstance().getOrders()!= null) {

            Set<Order> orders = Context.getInstance().getOrders();

            for (Order order : orders) {
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
        } else {
            totalRevenue.setText("0");
            revenueOnPC.setText("0");
            revenueOnShop.setText("0");
        }

    }

    @SneakyThrows
    public void endSession(ActionEvent actionEvent) {

        if(Context.getInstance().getReports() != null) {

            Reports reports = Context.getInstance().getReports();

            Set<Order> orderSet = Context.getInstance().getOrders();

            int profit = 0;

            for (Order order : orderSet) {
                profit += order.getCost();
            }

            Date endDate = new Date();

            reports.setEndDate(endDate);

            long diff = endDate.getTime() - reports.getStartDate().getTime();

            reports.setTimeSession(new Time((int) TimeUnit.MILLISECONDS.toHours(diff),
                    (int) TimeUnit.MILLISECONDS.toMinutes(diff),
                    (int) TimeUnit.MILLISECONDS.toSeconds(diff)));

            reports.setOrder(orderSet);

            reports.setProfit(profit);

            Context.getInstance().setReports(reports);

            reportsService.save(reports);

            Stage stage = new Stage();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/report.fxml")));

            stage.setScene(new Scene(root));
            stage.setTitle("Отчёт за смену");
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/image/x.jpg"))));
            stage.show();

            Context.getInstance().setReportsWindowController(this);

            Button source = (Button) actionEvent.getSource();
            source.getScene().getWindow().hide();

        } else {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Смена не открыта");
        }
    }
}
