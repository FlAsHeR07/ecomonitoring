package monitoring.repository;

import monitoring.model.DevelopmentProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevelopmentProgramRepository extends JpaRepository<DevelopmentProgram, Long> {
}