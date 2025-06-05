package monitoring.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enterprise {

    @Id
    @Column(columnDefinition = "bigint")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enterprise_gen")
    @SequenceGenerator(name = "enterprise_gen", sequenceName = "enterprise_seq", allocationSize = 1)
    private Long id;

    private String name;
    private String type;
    private String address;

    @ElementCollection
    @CollectionTable(name = "enterprise_location", joinColumns = @JoinColumn(name = "enterprise_id"))
    private List<Double> location;

    @Embedded
    private EnvironmentalImpact environmentalImpact;

    @ElementCollection
    @CollectionTable(name = "monitoring_systems", joinColumns = @JoinColumn(name = "enterprise_id"))
    private List<String> monitoringSystems;

    @Embedded
    private Parameters parameters;

    //////////////////////////////////////////////////

    @JsonManagedReference
    @OneToMany(mappedBy = "enterprise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IndicatorEntry> indicators = new ArrayList<>();
}

