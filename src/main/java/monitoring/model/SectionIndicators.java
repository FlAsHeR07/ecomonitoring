package monitoring.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Map;

@Entity
@Data
public class SectionIndicators {

    @Id
    @Column(columnDefinition = "bigint")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detailed_indicator_seq")
    @SequenceGenerator(name = "detailed_indicator_seq", sequenceName = "detailed_indicator_seq", allocationSize = 1)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "measured_values", joinColumns = @JoinColumn(name = "section_id"))
    @MapKeyColumn(name = "indicator_name")
    @Column(name = "indicator_value")
    private Map<String, Double> measured;

    @ElementCollection
    @CollectionTable(name = "calculated_values", joinColumns = @JoinColumn(name = "section_id"))
    @MapKeyColumn(name = "indicator_name")
    @Column(name = "indicator_value")
    private Map<String, Double> calculated;
}

