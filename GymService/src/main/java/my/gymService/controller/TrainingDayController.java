package my.gymService.controller;

import my.gymService.model.TrainingDay;
import my.gymService.repository.TrainingDayRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
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

        trainingDay.setType(newTrainingDay.getType());
        trainingDay.setCreatedAt(LocalDate.now());
        trainingDay.setExercises(newTrainingDay.getExercises());
        trainingDayRepository.save(trainingDay);
    }
}
