package ru.xgame.pavlovo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reports")
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "time_of_day")
    private String timeOfDay;

    @Column(name = "profit")
    private int profit;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn
    private Order order;
}
