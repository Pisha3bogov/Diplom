package ru.xgame.pavlovo.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.xgame.pavlovo.model.User;
import ru.xgame.pavlovo.service.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class UsersWindowController implements Initializable {
    public Label accountName;
    public ImageView imageAccount;
    public TableView<User> tableView;
    public TableColumn<User,String> loginColumn;
    public TableColumn<User,String> nameColumn;
    public TableColumn<User,String> lastNameColumn;
    public TableColumn<User,String> numberColumn;
    public TableColumn<User,Integer> balanceColumn;
    public TableColumn<User,Integer> saleColumn;
    public TextField searchTextField;

    ObservableList<User> users = FXCollections.observableArrayList();
    private final UserService userService;

    public UsersWindowController() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        this.userService = new UserService(factory);
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

        accountName.setText(Context.getInstance().getStartWin().getAdmin().getLogin());

        searchTextField();

        initTable();

        tableView.getSelectionModel().selectedItemProperty().addListener((obj,oldValue,newValue) -> {
            try {
                openAddBalance(newValue);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void searchTextField() {
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            FilteredList<User> userFilteredList = new FilteredList<>(users,
                    s -> s.getLogin().toLowerCase().contains(newValue.toLowerCase().trim()));
            tableView.setItems(userFilteredList);
        });
    }

    private void openAddBalance(User user) throws IOException {
        if(user != null) {
            Context.getInstance().setUser(user);

            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/add_balance.fxml"));
            AnchorPane anchorPane = loader.load();

            AddBalanceController addBalanceController = loader.getController();
            addBalanceController.setDate(user);

            stage.setTitle("Пополнение");
            stage.setScene(new Scene(anchorPane));
            stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/image/x.jpg"))));
            stage.show();
        }
    }

    private void initTable(){

        for (int i = 0; i < tableView.getItems().size(); i++){
            tableView.getItems().clear();
        }

        users.addAll(userService.findAll());

        tableView.setEditable(true);

        loginColumn.setCellValueFactory(u -> new SimpleObjectProperty<>(u.getValue().getLogin()));
        nameColumn.setCellValueFactory(u -> new SimpleObjectProperty<>(u.getValue().getName()));
        lastNameColumn.setCellValueFactory(u -> new SimpleObjectProperty<>(u.getValue().getLastName()));
        numberColumn.setCellValueFactory(u -> new SimpleObjectProperty<>(u.getValue().getNumber()));
        balanceColumn.setCellValueFactory(u -> new SimpleObjectProperty<>(u.getValue().getBalance()));
        saleColumn.setCellValueFactory(u -> new SimpleObjectProperty<>(u.getValue().getSale().getSale()));

        tableView.setItems(users);

        new Timeline(new KeyFrame(
                Duration.seconds(5),
                i -> initTable()))
                .play();
    }
}
