package my.gymService.repository;

import my.gymService.model.DayExercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayExerciseRepository extends JpaRepository<DayExercise, Long> {
}
