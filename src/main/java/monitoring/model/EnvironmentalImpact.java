package monitoring.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class EnvironmentalImpact {
    private int air;
    private int water;
    private int soil;
}

