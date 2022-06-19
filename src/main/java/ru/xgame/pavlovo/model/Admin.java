package ru.xgame.pavlovo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String priority;

    public Admin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Admin(String login, String password, String priority) {
        this.login = login;
        this.password = password;
        this.priority = priority;
    }
}
