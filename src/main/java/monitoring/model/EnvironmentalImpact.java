package monitoring.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class EnvironmentalImpact {
    private int air;
    private int water;
    private int soil;

    public EnvironmentalImpact(int air, int water, int soil) {
        this.air = air;
        this.water = water;
        this.soil = soil;
    }

    public EnvironmentalImpact() {
    }
}

