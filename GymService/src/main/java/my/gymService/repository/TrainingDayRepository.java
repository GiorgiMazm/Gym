package my.gymService.repository;

import my.gymService.model.TrainingDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingDayRepository extends JpaRepository<TrainingDay, Long> {
    List<TrainingDay> findByType(String type);
}
