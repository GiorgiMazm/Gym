package my.gymService.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Data;
import lombok.ToString;

@Data
@Entity(name = "day_exercise")
@EntityListeners(AuditingEntityListener.class)
public class DayExercise {

  @Id
  @JsonIgnore
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @ToString.Exclude
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "training_day_id", nullable = false)
  private TrainingDay trainingDay;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "dayExercise")
  private List<ExerciseSet> sets = new ArrayList<>();

  public DayExercise() {}

  public DayExercise(String name) {
    this.name = name;
  }

  @PrePersist
  void assigntToSets() {
    if (sets == null) {
      return;
    }
    sets.forEach(e -> e.setDayExercise(this));
  }
}
