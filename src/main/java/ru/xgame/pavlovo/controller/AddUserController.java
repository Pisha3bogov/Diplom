package ru.xgame.pavlovo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.xgame.pavlovo.model.Order;
import ru.xgame.pavlovo.model.Sale;
import ru.xgame.pavlovo.model.User;
import ru.xgame.pavlovo.service.OrderService;
import ru.xgame.pavlovo.service.SaleService;
import ru.xgame.pavlovo.service.UserService;

import java.net.URL;
import java.util.ResourceBundle;

public class AddUserController implements Initializable {
    public TextField loginField;
    public TextField numberField;
    public TextField nameField;
    public TextField lastNameField;
    public PasswordField passwordField;
    public PasswordField confirmationField;
    public ChoiceBox<String> saleChoose;
    public TextField balanceUpField;
    
    private final SaleService saleService;

    private final UserService userService;

    private final OrderService orderService;
    public Label errorLabel;

    public AddUserController() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        this.saleService = new SaleService(factory);
        this.userService = new UserService(factory);
        this.orderService = new OrderService(factory);
    }

    public void addUser(ActionEvent actionEvent) {
        if (loginField.getText().equals("")) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Введите логин");
        } else if (nameField.getText().equals("")) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Введите имя");
        } else if (passwordField.getText().equals("")) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Введите пароль");
        } else if (confirmationField.getText().equals("")) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Введите подтверждение пароля");
        } else if(!passwordField.getText().equals(confirmationField.getText())) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Пароли не совпадают");
        } else if(Integer.parseInt(balanceUpField.getText()) < 0) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Баланс не может быть меньше нуля");
        } else if (Context.getInstance().getReports() == null) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Смена не открыта");
        } else {
            int balance = 0;
            balance += Integer.parseInt(balanceUpField.getText());
            User user = new User(loginField.getText(),nameField.getText(),
                    lastNameField.getText(),numberField.getText(),
                    passwordField.getText(),balance,saleService.findById(Integer.valueOf(saleChoose.getValue())));
            userService.save(user);

            if(balance != 0){
                Order order = new Order("PC", balance, user);
                orderService.save(order);
                Context.getInstance().getOrders().add(order);
            }

            Button source = (Button) actionEvent.getSource();
            source.getScene().getWindow().hide();
        }
    }

    public void exit(ActionEvent actionEvent) {
        Button source = (Button) actionEvent.getSource();
        source.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Sale sale: saleService.findAll()){
            saleChoose.getItems().add(String.valueOf(sale.getSale()));
        }
        saleChoose.setValue(String.valueOf(saleService.findById(0).getSale()));
    }
}
