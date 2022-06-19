package ru.xgame.pavlovo.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.xgame.pavlovo.model.Order;
import ru.xgame.pavlovo.model.User;
import ru.xgame.pavlovo.service.OrderService;
import ru.xgame.pavlovo.service.UserService;

public class AddBalanceController {
    public TextField loginTextField;
    public TextField balanceTextField;
    public TextField count;
    public Label errorLabel;
    public TextArea comment;

    private User user;

    private final UserService userService;

    private final OrderService orderService;

    public AddBalanceController() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        this.userService = new UserService(factory);
        this.orderService = new OrderService(factory);
    }

    public void addBalance(ActionEvent actionEvent) {
        int balance = user.getBalance();
        int replenishment = Integer.parseInt(count.getText());
        int nullbalance = balance + replenishment;

        System.out.println(replenishment);
        System.out.println(nullbalance);

        if(Context.getInstance().getReports() != null) {
            if (replenishment > 0) {
                balance += replenishment;
                user.setBalance(balance);
                userService.update(user);
                exit(actionEvent);
                Order order = new Order("PC", replenishment, user);
                orderService.save(order);
                Context.getInstance().getOrders().add(order);

            } else if (replenishment == 0) {
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Введите сумму пополнения");
            } else if (comment.getText().equals("")) {
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Введите причину возврата");
            } else if (nullbalance < 0) {
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Операция не возможна");
            } else {
                balance += replenishment;
                user.setBalance(balance);
                userService.update(user);
                exit(actionEvent);
                Order order = new Order("PC", replenishment, user);
                orderService.save(order);
                Context.getInstance().getOrders().add(order);
            }
        }else {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Смена не открыта");
        }
    }

    public void exit(ActionEvent actionEvent) {
        Button source = (Button) actionEvent.getSource();
        source.getScene().getWindow().hide();
    }

    public void setDate(User user){
        this.user = user;
        loginTextField.setText(user.getLogin());
        balanceTextField.setText(String.valueOf(user.getBalance()));
        count.setText("0");
    }
}
