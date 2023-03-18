package my.gymService.controller;

import my.gymService.model.TrainingDay;
import my.gymService.repository.TrainingDayRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        if (trainingDay.getType() == null || Objects.equals(trainingDay.getType().trim(), "")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exercise type can not be empty");
        }
        if (trainingDay.getTrainingDate() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Training Date can not be empty");
        }

        trainingDay.getExercises().forEach(exercise -> {
            if (exercise.getName() == null || exercise.getName().trim().equals(""))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exercise name can not be empty");
            exercise.getSets().forEach(set -> {
                if (set.getRepetition() < 1)
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Repetition can not be smaller then 1");
                if (set.getWeight() < 1)
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Weight can not be smaller then 1");
            });
        });
    }
}
