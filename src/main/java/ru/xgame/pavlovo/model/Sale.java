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
@Table(name = "sale")
public class Sale {

    @Id
    @Column(name = "sale", nullable = false)
    private int sale;

    @Column(name = "cost_sale",nullable = false )
    private int costSale;


    @OneToMany(mappedBy = "sale")
    Set<User> user;

    @Override
    public String toString() {
        return "Sale{" +
                "sale=" + sale +
                ", costSale=" + costSale +
                '}';
    }
}
