package whatsapp_flow_challenge.controller;

import org.springframework.web.bind.annotation.*;
import whatsapp_flow_challenge.entity.FlowStep;
import whatsapp_flow_challenge.repository.FlowStepRepository;

@RestController
@RequestMapping("/flow-steps")
public class FlowStepController {

    private final FlowStepRepository repository;

    public FlowStepController(FlowStepRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public FlowStep create(@RequestBody FlowStep flowStep) {
        return repository.save(flowStep);
    }
}
