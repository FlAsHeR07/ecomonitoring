package monitoring.controller;

import lombok.RequiredArgsConstructor;
import monitoring.model.Enterprise;
import monitoring.repository.EnterpriseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enterprises")
@RequiredArgsConstructor
public class EnterpriseController {

    private final EnterpriseRepository repository;

    @GetMapping
    public List<Enterprise> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Enterprise create(@RequestBody Enterprise enterprise) {
        return repository.save(enterprise);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enterprise> getById(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}

