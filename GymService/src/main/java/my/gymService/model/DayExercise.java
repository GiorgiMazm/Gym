package my.gymService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;

@Entity(name = "day_exercise")
@Data
@EntityListeners(AuditingEntityListener.class)
public class DayExercise {

    @jakarta.persistence.Id
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @JsonIgnore
    @Column(name = "day_id", nullable = false, updatable = false)
    private Long dayId;


    @OneToMany(targetEntity = ExerciseSet.class)
    @JoinColumn(name = "day_exercise_id", referencedColumnName = "id")
    private Set<ExerciseSet> sets;

    public DayExercise() {
    }

    public DayExercise(String name, Long dayId) {
        this.name = name;
        this.dayId = dayId;

    }
}
