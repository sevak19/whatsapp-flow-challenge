package whatsapp_flow_challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whatsapp_flow_challenge.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}