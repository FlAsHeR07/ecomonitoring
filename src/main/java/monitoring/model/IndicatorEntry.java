package monitoring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import monitoring.converter.TimeSeriesConverter;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Data
public class IndicatorEntry {
    @Id
    @Column(columnDefinition = "bigint")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "indicatorEntry_gen")
    @SequenceGenerator(name = "indicatorEntry_gen", sequenceName = "indicatorEntry_seq", allocationSize = 1)
    private Long id;

    private String section; // air, water, soil...
    private String parameter; // NO2, SO2, etc.

    @Enumerated(EnumType.STRING)
    private IndicatorType type; // MEASURED or CALCULATED

    @Convert(converter = TimeSeriesConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<LocalDate, Double> values;

    @JsonBackReference
    @ManyToOne
    private Enterprise enterprise;

    public IndicatorEntry(String section, String parameter, IndicatorType type, Map<LocalDate, Double> values, Enterprise enterprise) {
        this.section = section;
        this.parameter = parameter;
        this.type = type;
        this.values = values;
        this.enterprise = enterprise;
    }

    public IndicatorEntry() {
    }
}
