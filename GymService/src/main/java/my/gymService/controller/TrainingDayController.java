package my.gymService.controller;

import my.gymService.model.TrainingDay;
import my.gymService.repository.TrainingDayRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping
public class TrainingDayController {
    TrainingDayRepository trainingDayRepository;

    public TrainingDayController(TrainingDayRepository trainingDayRepository) {
        this.trainingDayRepository = trainingDayRepository;
    }

    @GetMapping(path = "/allTrainingDays")
    public List<TrainingDay> home() {
        return trainingDayRepository.findAll();
    }

    @PostMapping("newTrainingDay")
    public void registerNewTrainingDay(@RequestBody TrainingDay trainingDay) {
        trainingDay.setCreatedAt(LocalDate.now());
        trainingDayRepository.save(trainingDay);
    }

    @DeleteMapping(path = "deleteDay/{TrainingDayId}")
    public void deleteTrainingDay(@PathVariable("TrainingDayId") Long trainingDayId) {
        trainingDayRepository.deleteById(trainingDayId);
    }

    @PutMapping(path = "editTrainingDay/{trainingDayId}")
    public void updateTrainingDay(@PathVariable(value = "trainingDayId") Long trainingDayId, @RequestBody TrainingDay newTrainingDay) {
        TrainingDay trainingDay = trainingDayRepository.findById(trainingDayId).get();
        System.out.println(trainingDay);
        System.out.println(newTrainingDay);

        trainingDay.setType(newTrainingDay.getType());
        trainingDay.setCreatedAt(LocalDate.now());
        trainingDay.setExercises(newTrainingDay.getExercises());
        trainingDayRepository.save(trainingDay);
    }
}
