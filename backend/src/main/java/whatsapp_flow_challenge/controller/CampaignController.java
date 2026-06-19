package whatsapp_flow_challenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import whatsapp_flow_challenge.entity.Campaign;
import whatsapp_flow_challenge.service.CampaignService;

import java.util.List;

@RestController
@RequestMapping("/campaigns")
public class CampaignController {

    private final CampaignService service;

    public CampaignController(CampaignService service) {
        this.service = service;
    }

    @PostMapping
    public Campaign create(@RequestBody Campaign campaign) {
        return service.save(campaign);
    }

    @GetMapping
    public List<Campaign> list() {
        return service.findAll();
    }

    @PostMapping("/{id}/dispatch")
    public ResponseEntity<String> dispatch(@PathVariable Long id) {
        service.dispatch(id);
        return ResponseEntity.ok("Campanha disparada com sucesso");
    }
}
