package my.gymService;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import my.gymService.model.DayExercise;
import my.gymService.model.ExerciseSet;
import my.gymService.model.TrainingDay;
import my.gymService.repository.TrainingDayRepository;

@SpringBootTest
@ActiveProfiles("test")
public class PersistenceTest {

  @Autowired TrainingDayRepository repo;

  @Test
  @Transactional
  void persistCascades() throws Exception {
    var training = new TrainingDay("intense workout");
    training.addExercise(dayExercise("lazy", exerciseSet(10, 20), exerciseSet(10, 25)));
    training.addExercise(dayExercise("motivated", exerciseSet(100, 50)));
    training.addExercise(dayExercise("insane", exerciseSet(255, 125), exerciseSet(299, 130)));

    repo.save(training);

    var allDays = repo.findByType("intense workout");
    assertThat(allDays).hasSize(1);

    var day = allDays.get(0);
    assertThat(day.getType()).isEqualTo("intense workout");
    assertThat(day.getExercises()).hasSize(3);

    var first = day.getExercises().get(0);
    assertThat(first.getName()).isEqualTo("lazy");
    assertThat(first.getSets()).hasSize(2);
    assertThat(first.getSets()).extracting(s -> s.getRepetition()).containsExactly(10, 10);
    assertThat(first.getSets()).extracting(s -> s.getWeight()).containsExactly(20, 25);

    var second = day.getExercises().get(1);
    assertThat(second.getName()).isEqualTo("motivated");
    assertThat(second.getSets()).hasSize(1);
    assertThat(second.getSets()).extracting(s -> s.getRepetition()).containsExactly(100);
    assertThat(second.getSets()).extracting(s -> s.getWeight()).containsExactly(50);

    var third = day.getExercises().get(2);
    assertThat(third.getName()).isEqualTo("insane");
    assertThat(third.getSets()).hasSize(2);
    assertThat(third.getSets()).extracting(s -> s.getRepetition()).containsExactly(255, 299);
    assertThat(third.getSets()).extracting(s -> s.getWeight()).containsExactly(125, 130);
  }

  static DayExercise dayExercise(String name, ExerciseSet... sets) {
    var e = new DayExercise(name);
    e.getSets().addAll(Arrays.asList(sets));
    return e;
  }

  static ExerciseSet exerciseSet(int rep, int weight) {
    var s = new ExerciseSet(rep, weight);
    return s;
  }
}
