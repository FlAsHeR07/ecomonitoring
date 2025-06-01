package monitoring.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Parameters {
    private int energyConsumption;
    private int wasteProduction;
    private int carbonEmissions;
    private int waterUsage;
}

