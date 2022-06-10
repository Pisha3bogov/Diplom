package ru.xgame.pavlovo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "order_on")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int cost;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "shop")
    private Shop shop;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    private User user;

    @OneToMany(mappedBy = "order")
    Set<Reports> reports;

    public Order(int id, String name, int cost, Shop shop_name_product) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.shop = shop_name_product;
    }

    public Order(String name, int cost, Shop shop_name_product) {
        this.name = name;
        this.cost = cost;
        this.shop = shop_name_product;
    }

    public Order(String name, int cost, User user_login_user) {
        this.name = name;
        this.cost = cost;
        this.user = user_login_user;
    }

    public Order(int id, String name, int cost, User user_login_user) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.user = user_login_user;
    }
}
