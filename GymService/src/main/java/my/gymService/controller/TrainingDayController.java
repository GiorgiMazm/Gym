package my.gymService.controller;

import my.gymService.model.TrainingDay;
import my.gymService.repository.TrainingDayRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
