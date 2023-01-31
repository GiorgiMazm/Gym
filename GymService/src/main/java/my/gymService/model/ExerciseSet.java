package my.gymService.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

@Entity(name = "exercise_set")
@Data
@EntityListeners(AuditingEntityListener.class)
public class ExerciseSet {

  @Id
  @JsonIgnore
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  private Long id;

  @Column(name = "repetition", nullable = false)
  private int repetition;

  @Column(name = "weight", nullable = false)
  private int weight;

  @ToString.Exclude
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "day_exercise_id")
  private DayExercise dayExercise;

  public ExerciseSet() {}

  public ExerciseSet(int repetition, int weight) {
    this.repetition = repetition;
    this.weight = weight;
  }
}
