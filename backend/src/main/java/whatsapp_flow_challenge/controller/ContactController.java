package whatsapp_flow_challenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import whatsapp_flow_challenge.entity.Contact;
import whatsapp_flow_challenge.service.ContactService;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService service;

    public ContactController(ContactService service) {
        this.service = service;
    }

    @PostMapping
    public Contact create(@RequestBody Contact contact) {
        return service.save(contact);
    }

    @GetMapping
    public List<Contact> list() {
        return service.findAll();
    }

    @PostMapping("/import")
    public ResponseEntity<String> importCsv(@RequestParam("file") MultipartFile file) {
        try {
            int count = service.importCsv(file);
            return ResponseEntity.ok(count + " contatos importados com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }
}
