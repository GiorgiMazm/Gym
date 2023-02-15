package my.gymService.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity(name = "training_day")
@EntityListeners(AuditingEntityListener.class)
public class TrainingDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainingDay", fetch = FetchType.EAGER)
    private List<DayExercise> exercises = new ArrayList<>();

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "training_date", nullable = false)
    private LocalDate trainingDate;

    public TrainingDay() {
    }

    public TrainingDay(String type) {
        this.type = type;
    }

    public void addExercise(DayExercise set) {
        exercises.add(set);
    }

    public void setExercises(Collection<DayExercise> newExercises) {
        exercises.forEach(s -> s.setTrainingDay(null));
        exercises = new ArrayList<>(newExercises);
    }

    @PrePersist
    @PreUpdate
    void jpaAssign() {
        exercises.forEach(e -> e.setTrainingDay(this));
    }
}
