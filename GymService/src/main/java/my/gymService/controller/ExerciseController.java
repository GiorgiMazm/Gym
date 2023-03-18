package my.gymService.controller;

import lombok.extern.slf4j.Slf4j;
import my.gymService.model.Exercise;
import my.gymService.repository.ExerciseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping
@Slf4j
public class ExerciseController {

    ExerciseRepository exerciseRepository;

    public ExerciseController(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @GetMapping(path = "allExercises")
    public List<Exercise> getAllExercise() {
        return exerciseRepository.findAll();
    }

    @GetMapping(path = "exercise/{exerciseId}")
    public Optional<Exercise> getExercise(@PathVariable("exerciseId") Long exerciseId) {
        return exerciseRepository.findById(exerciseId);
    }


    @PostMapping("newExercise")
    public void registerNewExercise(@RequestBody Exercise exercise) {
        checkExercise(exercise);
        exerciseRepository.save(exercise);
    }

    @DeleteMapping(path = "deleteExercise/{exerciseId}")
    public void deleteExercise(@PathVariable("exerciseId") Long exerciseId) {
        exerciseRepository.deleteById(exerciseId);
    }

    @PutMapping(path = "editExercise/{exerciseId}")
    public void updateExercise(@PathVariable(value = "exerciseId") Long exerciseId, @RequestBody Exercise newExercise) {
        Exercise exercise = exerciseRepository.findById(exerciseId).get();
        log.info(String.valueOf(exercise));

        checkExercise(newExercise);

        exercise.setName(newExercise.getName());
        exercise.setDifficulty(newExercise.getDifficulty());
        exercise.setDescription(newExercise.getDescription());
        exercise.setMuscleGroup(newExercise.getMuscleGroup());
        exerciseRepository.save(exercise);
    }

    private void checkExercise(Exercise exercise) {
        if (exercise.getName() == null || Objects.equals(exercise.getName().trim(), ""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exercise name can not be empty");

        if (exercise.getDescription() == null || Objects.equals(exercise.getDescription().trim(), ""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Description can not be empty");
        if (exercise.getDifficulty() == null || Objects.equals(exercise.getDifficulty().trim(), ""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Difficulty can not be empty");

        if (exercise.getMuscleGroup() == null || Objects.equals(exercise.getMuscleGroup().trim(), ""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Muscle group can not be empty");

        if (exerciseRepository.findByName(exercise.getName()) != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exercise name is already taken");
    }
}
