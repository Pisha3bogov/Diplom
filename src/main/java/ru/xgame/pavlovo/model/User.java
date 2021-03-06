package ru.xgame.pavlovo.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @NotNull
    @NonNull
    @Column(name = "login_user",nullable = false)
    private String login;

    @Column(name = "first_name",nullable = false)
    private String name;

    @Column(name = "last_name")
    private String lastName;


    @Column(name = "number")
    private String number;

    @Column(name = "password",nullable = false)
    private  String password;

    @Column(name = "balance",nullable = false)
    private int balance;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn
    private Sale sale;

    @OneToMany(mappedBy = "user")
    Set<Order> orders;

    public User(@NonNull String login, String name, String password, int balance, Sale sale) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.sale = sale;
    }

    public User(@NonNull String login, String name, String lastName, String number, String password, int balance, Sale sale) {
        this.login = login;
        this.name = name;
        this.lastName = lastName;
        this.number = number;
        this.password = password;
        this.balance = balance;
        this.sale = sale;
    }
}
