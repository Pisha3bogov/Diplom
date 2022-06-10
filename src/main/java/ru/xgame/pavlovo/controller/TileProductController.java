package ru.xgame.pavlovo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import ru.xgame.pavlovo.model.Shop;

import java.util.Objects;

public class TileProductController {
    public ImageView imageView;
    public Label countLabel;
    public Label costLabel;
    public Label nameLabel;

    private Shop shop;

    public void setData(Shop shop) {
        this.shop = shop;

        imageView.setImage(new Image(String.valueOf(getClass().getResource("/image/x_variable2.jpg"))));

        costLabel.setText("Цена: " + String.valueOf(shop.getCost()));
        countLabel.setText("Кол-во: " + String.valueOf(shop.getCount()));
        nameLabel.setText(shop.getNameProduct());
    }

    @SneakyThrows
    public void openSceneOrder(ActionEvent actionEvent) {

        Context.getInstance().setTileProductController(this);

        Stage stage = new Stage();

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/order_window.fxml")));



        stage.setTitle("Заказ");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/image/x.jpg"))));
        stage.show();
    }

    public Shop getShop() {
        return shop;
    }
}
