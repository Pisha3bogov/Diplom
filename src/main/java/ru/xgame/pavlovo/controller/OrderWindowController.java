package ru.xgame.pavlovo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.xgame.pavlovo.model.Order;
import ru.xgame.pavlovo.model.Shop;
import ru.xgame.pavlovo.service.OrderService;
import ru.xgame.pavlovo.service.ShopService;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderWindowController implements Initializable {
    public Label nameProduct;
    public TextField costProduct;
    public TextField countProduct;
    public ImageView imageView;
    public Label errorLabel;

    private Shop shop;

    private int cost;

    private int count;

    private final ShopService shopService;

    private final OrderService orderService;

    public OrderWindowController() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        this.shopService = new ShopService(factory);
        this.orderService = new OrderService(factory);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageView.setImage(new Image(String.valueOf(getClass().getResource("/image/x_variable2.jpg"))));

        shop = Context.getInstance().getTileProductController().getShop();

        count = 1;

        countProduct.setText(String.valueOf(count));

        cost = shop.getCost() * count;

        nameProduct.setText(shop.getNameProduct());

        costProduct.setText(String.valueOf(cost));
    }

    public void addOrder(ActionEvent actionEvent) {
        if(shop.getCount() > 0) {
            exit(actionEvent);

            shop.setCount(shop.getCount() - count);

            shopService.update(shop);

            orderService.save(new Order("shop", cost, shop));

            Context.getInstance().getTileProductController().setData(shop);
        }else {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Продукции нет в наличии");
        }
    }

    public void exit(ActionEvent actionEvent) {
        Button source = (Button) actionEvent.getSource();
        source.getScene().getWindow().hide();
    }

    public void addCountProduct(ActionEvent actionEvent) {
        count++;

        cost = shop.getCost() * count;

        countProduct.setText(String.valueOf(count));

        costProduct.setText(String.valueOf(cost));
    }

    public void removeCountProduct(ActionEvent actionEvent) {
        count--;

        if (count < 0){
            count = 0;
        }

        cost = shop.getCost() * count;

        countProduct.setText(String.valueOf(count));

        costProduct.setText(String.valueOf(cost));
    }
}
