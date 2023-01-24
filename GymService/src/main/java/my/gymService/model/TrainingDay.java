package my.gymService.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity(name = "training_day")
@Data
@EntityListeners(AuditingEntityListener.class)
public class TrainingDay {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;


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
