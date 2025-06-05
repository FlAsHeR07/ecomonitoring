package monitoring.repository;

import monitoring.model.DevelopmentProgram;
import monitoring.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByDevelopmentProgram(DevelopmentProgram program);

}
