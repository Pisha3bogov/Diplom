package ru.xgame.pavlovo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.xgame.pavlovo.model.Shop;
import ru.xgame.pavlovo.service.ShopService;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ShopWindowController implements Initializable {
    public Label accountName;
    public ImageView imageAccount;
    public ScrollPane scrollPane;
    public TilePane tilePane;

    ObservableList<Shop> shops = FXCollections.observableArrayList();

    private final ShopService shopService;

    AnchorPane anchorPane;

    public ShopWindowController() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        this.shopService = new ShopService(factory);
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

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        shops.addAll(shopService.findAll());
        accountName.setText(Context.getInstance().getStartWin().getAdmin().getLogin());

        for(Shop shop: shops){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tileProduct.fxml"));
            anchorPane = loader.load();

            TileProductController tileProductController = loader.getController();
            tileProductController.setData(shop);

            tilePane.getChildren().add(anchorPane);
        }
    }
}
