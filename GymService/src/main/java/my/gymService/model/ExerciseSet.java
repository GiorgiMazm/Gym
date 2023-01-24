package my.gymService.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "exercise_set")
@Data
@EntityListeners(AuditingEntityListener.class)
public class ExerciseSet {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "repetition", nullable = false)
    private int repetition;

    @Column(name = "day_exercise_id", nullable = false, updatable = false)
    private Long dayExerciseId;

    public ExerciseSet() {
    }

    public ExerciseSet(int repetition, Long dayExerciseId) {
        this.repetition = repetition;
        this.dayExerciseId = dayExerciseId;
    }
}
