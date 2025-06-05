package monitoring.dto.response;

import java.time.LocalDate;
import java.util.List;

public class DevelopmentProgramResponseDTO {
    public Long id;
    public String name;
    public String description;
    public LocalDate dateStart;
    public LocalDate dateEnd;
    public double budget;
    public List<EventResponseDTO> events;
}

