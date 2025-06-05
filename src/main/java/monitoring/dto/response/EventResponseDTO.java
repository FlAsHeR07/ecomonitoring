package monitoring.dto.response;

import java.util.List;

public class EventResponseDTO {
    public Long id;
    public String name;
    public String description;
    public String category;
    public int daysCount;
    public int efficiency;
    public List<ResourcesResponseDTO> resources;
}

