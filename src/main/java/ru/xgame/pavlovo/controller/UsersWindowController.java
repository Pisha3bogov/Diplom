package ru.xgame.pavlovo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.util.Objects;

public class UsersWindowController {
    public Label accountName;
    public ImageView imageAccount;

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
}
