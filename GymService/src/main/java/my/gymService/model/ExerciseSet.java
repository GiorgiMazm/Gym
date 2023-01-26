package my.gymService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    @JoinColumn(name = "day_exercise_id", nullable = false)
    private DayExercise dayExercise;

    public ExerciseSet() {
    }

    public ExerciseSet(int repetition, int weight) {
        this.repetition = repetition;
        this.weight = weight;
    }
}
