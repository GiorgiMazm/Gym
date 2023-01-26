package my.gymService.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Set;

@Entity(name = "training_day")
@Data
@EntityListeners(AuditingEntityListener.class)
public class TrainingDay {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @OneToMany(targetEntity = DayExercise.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "training_day_id", referencedColumnName = "id")
    private Set<DayExercise> exercises;

    @Column(name = "type", nullable = false)
    private String type;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    public TrainingDay() {
    }

    public TrainingDay(String type) {
        this.type = type;
    }
}
