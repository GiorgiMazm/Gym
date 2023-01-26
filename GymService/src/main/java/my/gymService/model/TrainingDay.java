package my.gymService.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Data
@Entity(name = "training_day")
@EntityListeners(AuditingEntityListener.class)
public class TrainingDay {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  private Long id;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainingDay")
  private List<DayExercise> exercises = new ArrayList<>();

  @Column(name = "type", nullable = false)
  private String type;

  @CreatedDate
  @Column(name = "created_at", nullable = false)
  private LocalDate createdAt;

  public TrainingDay() {}

  public TrainingDay(String type) {
    this.type = type;
  }

  public void addExercise(DayExercise set) {
    exercises.add(set);
  }

  @PrePersist
  void assigntToExercises() {
    if (exercises == null) {
      return;
    }
    exercises.forEach(e -> e.setTrainingDay(this));
  }
}
