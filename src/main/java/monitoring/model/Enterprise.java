package monitoring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enterprise {

    @Id
    @Column(columnDefinition = "bigint")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enterprise_seq")
    @SequenceGenerator(name = "enterprise_seq", sequenceName = "enterprise_seq", allocationSize = 1)
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

    @OneToOne(cascade = CascadeType.ALL)
    private SectionIndicators air;

    @OneToOne(cascade = CascadeType.ALL)
    private SectionIndicators water;

    @OneToOne(cascade = CascadeType.ALL)
    private SectionIndicators soil;

    @OneToOne(cascade = CascadeType.ALL)
    private SectionIndicators radiation;

    @OneToOne(cascade = CascadeType.ALL)
    private SectionIndicators waste;

    @OneToOne(cascade = CascadeType.ALL)
    private SectionIndicators economic;

    @OneToOne(cascade = CascadeType.ALL)
    private SectionIndicators health;

    @OneToOne(cascade = CascadeType.ALL)
    private SectionIndicators energy;
}

