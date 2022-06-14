package ru.xgame.pavlovo.model;

import javafx.collections.ObservableList;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "start_date",nullable = false)
    private Date startDate;

    @Column(name = "time_session",nullable = false)
    private Time timeSession;

    @Column(name = "end_date",nullable = false)
    private Date endDate;
    @Column(name = "profit",nullable = false)
    private int profit;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "reports_order",
            joinColumns = {@JoinColumn(name = "reports_id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id")})
    private Set<Order> order = new HashSet<Order>();

    public Reports(Date startDate) {
        this.startDate = startDate;
    }


}
