package monitoring.controller;

import lombok.RequiredArgsConstructor;
import monitoring.dto.request.DevelopmentProgramDTO;
import monitoring.dto.request.EventDTO;
import monitoring.dto.request.ResourcesDTO;
import monitoring.dto.response.DevelopmentProgramResponseDTO;
import monitoring.dto.response.EventResponseDTO;
import monitoring.dto.response.ResourcesResponseDTO;
import monitoring.model.DevelopmentProgram;
import monitoring.model.Event;
import monitoring.model.Resources;
import monitoring.repository.DevelopmentProgramRepository;
import monitoring.repository.EventRepository;
import monitoring.repository.ResourcesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppController {

    private final DevelopmentProgramRepository developmentProgramRepository;
    private final EventRepository eventRepository;
    private final ResourcesRepository resourcesRepository;

    // ✅ POST /api/add/development-program
    @PostMapping("/add/development-program")
    public ResponseEntity<?> createDevelopmentProgram(@RequestBody DevelopmentProgramDTO dto) {
        DevelopmentProgram program = new DevelopmentProgram();
        program.setName(dto.name);
        program.setDescription(dto.description);
        program.setDateStart(dto.dateStart);
        program.setDateEnd(dto.dateEnd);
        program.setBudget(dto.budget);

        developmentProgramRepository.save(program);
        return ResponseEntity.status(HttpStatus.CREATED).body(program);
    }

    // ✅ POST /api/add/event
    @PostMapping("/add/event")
    public ResponseEntity<?> createEvent(@RequestBody EventDTO dto) {
        Event event = new Event();
        event.setName(dto.name);
        event.setDescription(dto.description);
        event.setCategory(dto.category);
        event.setDaysCount(dto.daysCount);
        event.setEfficiency(dto.efficiency);

        if (dto.developmentProgramId != null) {
            DevelopmentProgram dp = developmentProgramRepository
                    .findById(dto.developmentProgramId)
                    .orElseThrow(() -> new IllegalArgumentException("DevelopmentProgram not found"));
            event.setDevelopmentProgram(dp);
        }

        eventRepository.save(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }

    // ✅ POST /api/add/resources
    @PostMapping("/add/resources")
    public ResponseEntity<?> createResource(@RequestBody ResourcesDTO dto) {
        Resources res = new Resources();
        res.setName(dto.name);
        res.setAmount(dto.amount);
        res.setPrice(dto.price);

        if (dto.eventId != null) {
            Event event = eventRepository
                    .findById(dto.eventId)
                    .orElseThrow(() -> new IllegalArgumentException("Event not found"));
            res.setEvent(event);
        }

        resourcesRepository.save(res);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    // ✅ GET /api/development-programs
    @GetMapping("/development-programs")
    public ResponseEntity<List<DevelopmentProgramResponseDTO>> getAllDevelopmentPrograms() {
        List<DevelopmentProgram> programs = developmentProgramRepository.findAll();

        List<DevelopmentProgramResponseDTO> response = programs.stream().map(program -> {
            DevelopmentProgramResponseDTO dto = new DevelopmentProgramResponseDTO();
            dto.id = program.getId();
            dto.name = program.getName();
            dto.description = program.getDescription();
            dto.dateStart = program.getDateStart();
            dto.dateEnd = program.getDateEnd();
            dto.budget = program.getBudget();

            // Находим все Event, привязанные к данной программе
            List<Event> events = eventRepository.findByDevelopmentProgram(program);

            dto.events = events.stream().map(event -> {
                EventResponseDTO eventDto = new EventResponseDTO();
                eventDto.id = event.getId();
                eventDto.name = event.getName();
                eventDto.description = event.getDescription();
                eventDto.category = event.getCategory();
                eventDto.daysCount = event.getDaysCount();
                eventDto.efficiency = event.getEfficiency();

                // Находим ресурсы, привязанные к событию
                List<Resources> resources = resourcesRepository.findByEvent(event);

                eventDto.resources = resources.stream().map(res -> {
                    ResourcesResponseDTO resDto = new ResourcesResponseDTO();
                    resDto.id = res.getId();
                    resDto.name = res.getName();
                    resDto.amount = res.getAmount();
                    resDto.price = res.getPrice();
                    return resDto;
                }).toList();

                return eventDto;
            }).toList();

            return dto;
        }).toList();

        return ResponseEntity.ok(response);
    }
}

