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

    @DeleteMapping(path = "delete{TrainingDayId}")
    public void deleteTrainingDay(@PathVariable("TrainingDayId") Long trainingDayId) {
        trainingDayRepository.deleteById(trainingDayId);
    }
}
