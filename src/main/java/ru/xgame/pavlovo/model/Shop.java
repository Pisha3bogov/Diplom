package ru.xgame.pavlovo.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
}
