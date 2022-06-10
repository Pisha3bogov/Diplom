package ru.xgame.pavlovo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.xgame.pavlovo.model.Admin;
import ru.xgame.pavlovo.service.AdminService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class StartWindowController {
    private final AdminService adminService;

    public PasswordField passwordField;

    public TextField loginField;

    public Label Error;

    private Admin admin;

    public StartWindowController() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        this.adminService = new AdminService(factory);
    }

    public void OpenMain(ActionEvent actionEvent) {

        admin = adminService.findByLogin(loginField.getText());
        final boolean exit = admin.getPassword().equals(passwordField.getText());

        try {
           if(exit) {
               Context.getInstance().setStartWin(this);

                Button input = (Button) actionEvent.getSource();
                input.getScene().getWindow().hide();

                openSceneMain();
           }else {
               Error.setTextFill(Color.RED);
               Error.setText("Логин или пароль введены не верно");
           }
        }catch (NullPointerException e){
            Error.setTextFill(Color.RED);
            Error.setText("Введите пароль");
        }
    }

    public void Exit(ActionEvent actionEvent) {
        final Button source = (Button) actionEvent.getSource();
        source.getScene().getWindow().hide();
    }

    private void openSceneMain() {
        Stage stage = new Stage();

        Parent root = null;

        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/general_window.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert root != null;
        stage.setScene(new Scene(root));
        stage.setTitle("Главное меню");
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/image/x.jpg"))));
        stage.show();

    }

    private void openSceneRegistration(){
        Stage stage = new Stage();

        Parent root = null;

        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/registration.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("Регистрация");
        assert root != null;
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/image/x.jpg"))));
        stage.show();
    }

    public Admin getAdmin() {
        return admin;
    }
}