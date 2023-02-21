package my.gymService.controller;

import my.gymService.model.TrainingDay;
import my.gymService.repository.TrainingDayRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping
public class TrainingDayController {
    TrainingDayRepository trainingDayRepository;

    public TrainingDayController(TrainingDayRepository trainingDayRepository) {
        this.trainingDayRepository = trainingDayRepository;
    }

    @GetMapping(path = "/allTrainingDays")
    public List<TrainingDay> getAllTrainingDays() {
        return trainingDayRepository.findAll();
    }

    @GetMapping(path = "trainingDay/{trainingDayId}")
    public Optional<TrainingDay> getTrainingDay(@PathVariable("trainingDayId") Long trainingDayId) {
        return trainingDayRepository.findById(trainingDayId);
    }

    @PostMapping("newTrainingDay")
    public void registerNewTrainingDay(@RequestBody TrainingDay trainingDay) {
        checkTrainingDay(trainingDay);
        trainingDayRepository.save(trainingDay);
    }

    @DeleteMapping(path = "deleteDay/{TrainingDayId}")
    public void deleteTrainingDay(@PathVariable("TrainingDayId") Long trainingDayId) {
        trainingDayRepository.deleteById(trainingDayId);
    }

    @PutMapping(path = "editTrainingDay/{trainingDayId}")
    public void updateTrainingDay(@PathVariable(value = "trainingDayId") Long trainingDayId, @RequestBody TrainingDay newTrainingDay) {
        TrainingDay trainingDay = trainingDayRepository.findById(trainingDayId).get();
        checkTrainingDay(newTrainingDay);

        trainingDay.setType(newTrainingDay.getType());
        trainingDay.setTrainingDate(newTrainingDay.getTrainingDate());
        trainingDay.setExercises(newTrainingDay.getExercises());
        trainingDayRepository.save(trainingDay);
    }

    private void checkTrainingDay(TrainingDay trainingDay) {
        if (Objects.equals(trainingDay.getType().trim(), "")) {
            throw new IllegalArgumentException("Exercise type can not be empty");
        }
        trainingDay.getExercises().forEach(exercise -> {
            if (exercise.getName().trim().equals("")) throw new IllegalStateException("Exercise name can not be null");
            exercise.getSets().forEach(set -> {
                if (set.getRepetition() < 1) throw new IllegalStateException("Repetition can not be smaller then 1");
                if (set.getWeight() < 1) throw new IllegalStateException("Weight can not be smaller then 1");
            });
        });
    }
}
