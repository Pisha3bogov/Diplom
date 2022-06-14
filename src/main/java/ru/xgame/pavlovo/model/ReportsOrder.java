package ru.xgame.pavlovo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reports_order")
public class ReportsOrder {
    @Id
    @Column(name = "reports_id")
    private int reportsId;

    @Column(name = "order_id")
    private int orderId;
}
