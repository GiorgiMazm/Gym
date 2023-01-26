package my.gymService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

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

    public DayExercise() {
    }

    public DayExercise(String name) {
        this.name = name;
    }

    @PrePersist
    void assignToSets() {
        if (sets == null) {
            return;
        }
        sets.forEach(e -> e.setDayExercise(this));
    }
}
