package ru.xgame.pavlovo.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "shop")
public class Shop {

    @Id
    @Column(name = "name_product",nullable = false)
    private String nameProduct;

    @Column(name = "cost",nullable = false)
    private int cost;

    @Column(name = "count",nullable = false)
    private int count;

    @Column(name = "photo_path")
    private String photoPath;

    @OneToMany(mappedBy = "shop")
    Set<Order> orders;
}
