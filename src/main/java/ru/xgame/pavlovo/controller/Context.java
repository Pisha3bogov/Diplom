package ru.xgame.pavlovo.controller;

import ru.xgame.pavlovo.model.Admin;
import ru.xgame.pavlovo.model.Order;
import ru.xgame.pavlovo.model.Reports;
import ru.xgame.pavlovo.model.User;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

public class Context {
    private final static Context instance = new Context();
    public static Context getInstance() {
        return instance;
    }

    private Connection con;
    public void setConnection(Connection con) {
        this.con = con;
    }

    public Connection getConnection() {
        return con;
    }

    private StartWindowController startWin;

    public StartWindowController getStartWin() {
        return startWin;
    }

    public void setStartWin(StartWindowController startWin) {
        this.startWin = startWin;
    }

    public Admin getAdmin(){
        return startWin.getAdmin();
    }

    private TileProductController tileProductController;

    public TileProductController getTileProductController() {
        return tileProductController;
    }

    public void setTileProductController(TileProductController tileProductController) {
        this.tileProductController = tileProductController;
    }

    private Set<Order> orders = new HashSet<>();

    public Set<Order> getOrders() {
        return orders;
    }
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    private Reports reports;

    public Reports getReports() {
        return reports;
    }

    public void setReports(Reports reports) {
        this.reports = reports;
    }

    private ReportsWindowController reportsWindowController;

    public ReportsWindowController getReportsWindowController() {
        return reportsWindowController;
    }

    public void setReportsWindowController(ReportsWindowController reportsWindowController) {
        this.reportsWindowController = reportsWindowController;
    }

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
