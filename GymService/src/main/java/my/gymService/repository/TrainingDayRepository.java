package my.gymService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import my.gymService.model.TrainingDay;

public interface TrainingDayRepository extends JpaRepository<TrainingDay, Long> {
  List<TrainingDay> findByType(String type);
}
