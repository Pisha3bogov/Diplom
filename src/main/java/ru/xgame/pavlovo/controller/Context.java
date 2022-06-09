package ru.xgame.pavlovo.controller;

import ru.xgame.pavlovo.model.Admin;

import java.sql.Connection;

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

    private ResetPassword1Controller resPas;
    public void setResPas(ResetPassword1Controller resPas) {
        this.resPas = resPas;
    }

    public ResetPassword1Controller getResPas() {
        return resPas;
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
}
