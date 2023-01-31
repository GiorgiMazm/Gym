package my.gymService;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import my.gymService.model.DayExercise;
import my.gymService.model.ExerciseSet;
import my.gymService.model.TrainingDay;
import my.gymService.repository.TrainingDayRepository;

@SpringBootTest
@ActiveProfiles("test")
public class PersistenceTest {

  @Autowired TrainingDayRepository repo;
  @Autowired WithTransaction withTransaction;

  @Test
  void persistCascades() throws Exception {
    var id = saveExercise();

    var day = repo.findById(id).get();

    assertThat(day.getType()).isEqualTo("intense workout");
    assertThat(day.getExercises()).hasSize(3);

    var first = day.getExercises().get(0);
    assertThat(first.getName()).isEqualTo("lazy");
    assertThat(first.getSets())
        .hasSize(2)
        .anySatisfy(hasExerciseSet(10, 20))
        .anySatisfy(hasExerciseSet(10, 25));

    var second = day.getExercises().get(1);
    assertThat(second.getName()).isEqualTo("motivated");
    assertThat(second.getSets()).hasSize(1).anySatisfy(hasExerciseSet(100, 50));

    var third = day.getExercises().get(2);
    assertThat(third.getName()).isEqualTo("insane");
    assertThat(third.getSets())
        .hasSize(2)
        .anySatisfy(hasExerciseSet(255, 125))
        .anySatisfy(hasExerciseSet(299, 130));
  }

  @Test
  void updatExcercise() throws Exception {
    var id = saveExercise();

    withTransaction.run(
        () -> {
          var day = repo.findById(id).get();
          var newExercises = List.of(dayExercise("easy", exerciseSet(1, 10), exerciseSet(1, 12)));
          day.setExercises(newExercises);
          var newId = repo.save(day).getId();
          assertThat(newId).isEqualTo(id);
        });

    var day = repo.findById(id).get();

    var exercises = day.getExercises();
    assertThat(exercises).hasSize(1);

    var first = exercises.get(0);
    assertThat(first.getName()).isEqualTo("easy");
    assertThat(first.getSets())
        .hasSize(2)
        .anySatisfy(hasExerciseSet(1, 10))
        .anySatisfy(hasExerciseSet(1, 12));
  }

  private static Consumer<ExerciseSet> hasExerciseSet(int reps, int weight) {
    return set -> {
      assertThat(set.getRepetition()).isEqualTo(reps);
      assertThat(set.getWeight()).isEqualTo(weight);
    };
  }

  private Long saveExercise() {
    var training = new TrainingDay("intense workout");
    training.addExercise(dayExercise("lazy", exerciseSet(10, 20), exerciseSet(10, 25)));
    training.addExercise(dayExercise("motivated", exerciseSet(100, 50)));
    training.addExercise(dayExercise("insane", exerciseSet(255, 125), exerciseSet(299, 130)));

    return repo.save(training).getId();
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
