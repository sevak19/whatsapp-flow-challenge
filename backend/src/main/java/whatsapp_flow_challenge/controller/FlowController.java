package whatsapp_flow_challenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import whatsapp_flow_challenge.entity.Flow;
import whatsapp_flow_challenge.service.FlowService;

@RestController
@RequestMapping("/flows")
public class FlowController {

    private final FlowService service;

    public FlowController(FlowService service) {
        this.service = service;
    }

    @PostMapping
    public Flow create(@RequestBody Flow flow) {
        return service.save(flow);
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<String> start(@PathVariable Long id) {
        service.addContactsToFlow(id);
        return ResponseEntity.ok("Fluxo iniciado");
    }
}
