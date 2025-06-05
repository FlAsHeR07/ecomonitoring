package monitoring.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Event {

    @Id
    @Column(columnDefinition = "bigint")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_gen")
    @SequenceGenerator(name = "event_gen", sequenceName = "event_seq", allocationSize = 1)
    private Long id;

    private String name;
    private String description;
    private String category;
    private int daysCount;
    private int efficiency;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "development_program_id")
    private DevelopmentProgram developmentProgram;

    public Event() {}

    public Event(Long id, String name, String description, String category, int daysCount, int efficiency) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.daysCount = daysCount;
        this.efficiency = efficiency;
    }
}
