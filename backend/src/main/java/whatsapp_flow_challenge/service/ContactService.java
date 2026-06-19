package whatsapp_flow_challenge.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import whatsapp_flow_challenge.entity.Contact;
import whatsapp_flow_challenge.repository.ContactRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class ContactService {

    private final ContactRepository repository;

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    public Contact save(Contact contact) {
        return repository.save(contact);
    }

    public List<Contact> findAll() {
        return repository.findAll();
    }

    public int importCsv(MultipartFile file) throws Exception {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream()))) {

            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) { firstLine = false; continue; } // pula header
                String[] parts = line.split(",");
                if (parts.length < 2) continue;

                Contact contact = new Contact();
                contact.setName(parts[0].trim());
                contact.setPhone(parts[1].trim());
                repository.save(contact);
                count++;
            }
        }
        System.out.println("[CSV] " + count + " contatos importados.");
        return count;
    }
}
