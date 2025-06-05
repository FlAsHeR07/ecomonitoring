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

    public Parameters(int energyConsumption, int wasteProduction, int carbonEmissions, int waterUsage) {
        this.energyConsumption = energyConsumption;
        this.wasteProduction = wasteProduction;
        this.carbonEmissions = carbonEmissions;
        this.waterUsage = waterUsage;
    }

    public Parameters() {
    }
}

