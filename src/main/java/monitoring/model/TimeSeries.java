package monitoring.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Embeddable
@Data
public class TimeSeries {
    @ElementCollection
    @CollectionTable(name = "timeseries_values", joinColumns = @JoinColumn(name = "series_id"))
    @MapKeyColumn(name = "date")
    @Column(name = "value")
    private Map<LocalDate, Double> values = new HashMap<>();
}
