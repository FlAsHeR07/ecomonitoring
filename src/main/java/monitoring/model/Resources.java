package monitoring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Resources {

    @Id
    @Column(columnDefinition = "bigint")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resources_gen")
    @SequenceGenerator(name = "resources_gen", sequenceName = "resources_seq", allocationSize = 1)
    private Long id;

    private String name;
    private int amount;
    private double price;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Resources() {}

    public Resources(String name, int amount, double price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }
}
