package monitoring.dto.request;

import java.util.List;

public class EventDTO {
    public String name;
    public String description;
    public String category;
    public int daysCount;
    public int efficiency;
    public Long developmentProgramId; // 🔗 связь по ID
}

