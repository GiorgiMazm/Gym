package my.gymService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TrainingDayController {
    @GetMapping(path = "/allTrainingDays")
    public String home() {
        return "{ list of all training days}";
    }
}
