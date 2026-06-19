package whatsapp_flow_challenge.controller;

import org.springframework.web.bind.annotation.*;
import whatsapp_flow_challenge.entity.SendQueue;
import whatsapp_flow_challenge.service.SendQueueService;

@RestController
@RequestMapping("/queue")
public class SendQueueController {

    private final SendQueueService service;

    public SendQueueController(
            SendQueueService service
    ) {
        this.service = service;
    }

    @PostMapping
    public SendQueue create(
            @RequestBody SendQueue sendQueue
    ) {
        return service.save(sendQueue);
    }
}
