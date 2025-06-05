package monitoring.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class DevelopmentProgram {

    @Id
    @Column(columnDefinition = "bigint")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "program_gen")
    @SequenceGenerator(name = "program_gen", sequenceName = "program_seq", allocationSize = 1)
    private Long id;

    private String name;
    private String description;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private double budget;

    public DevelopmentProgram() {}

    public DevelopmentProgram(Long id, String name, String description, LocalDate dateStart, LocalDate dateEnd, double budget) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.budget = budget;
    }
}
