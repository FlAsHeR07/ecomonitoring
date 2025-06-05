package monitoring.repository;

import monitoring.model.Event;
import monitoring.model.Resources;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourcesRepository extends JpaRepository<Resources, Long> {
    List<Resources> findByEvent(Event event);
}