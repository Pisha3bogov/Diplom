package ru.xgame.pavlovo.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "hardware")
public class Hardware {

    @Id
    @Column(name = "idhardware")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHardware;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn
    private Type type;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn
    private Status status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn
    private User user;

    @Override
    public String toString() {
        return "Hardware{" +
                "idHardware=" + idHardware +
                ", type=" + type.getTypeName() +
                ", status=" + status.getName() +
                ", user=" + isEmpty(user) +
                '}';
    }

    private String isEmpty(User user){
        return user != null ? user.getName(): "null";
    }
}
