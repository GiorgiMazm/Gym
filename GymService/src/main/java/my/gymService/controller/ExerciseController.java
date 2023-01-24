package my.gymService.controller;

import lombok.extern.slf4j.Slf4j;
import my.gymService.repository.ExerciseRepository;
import my.gymService.model.Exercise;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@Slf4j
public class ExerciseController {

    ExerciseRepository exerciseRepository;

    public ExerciseController(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @GetMapping(path = "allExercises")
    public List<Exercise> test() {
        return exerciseRepository.findAll();
    }

    @PostMapping("newExercise")
    public void registerNewExercise(@RequestBody Exercise exercise) {
        exerciseRepository.save(exercise);
    }

    @DeleteMapping(path = "delete{exerciseId}")
    public void deleteExercise(@PathVariable("exerciseId") Long exerciseId) {
        exerciseRepository.deleteById(exerciseId);
    }

    @PutMapping(path = "edit{exerciseId}")
    public void updateExercise(@PathVariable(value = "exerciseId") Long exerciseId, @RequestBody Exercise newExercise) {
        Exercise exercise = exerciseRepository.findById(exerciseId).get();
        log.info(String.valueOf(exercise));

        exercise.setName(newExercise.getName());
        exercise.setDifficulty(newExercise.getDifficulty());
        exercise.setDescription(newExercise.getDescription());
        exercise.setMuscleGroup(newExercise.getMuscleGroup());
        exerciseRepository.save(exercise);
    }
}
