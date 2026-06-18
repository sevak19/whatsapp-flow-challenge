package whatsapp_flow_challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whatsapp_flow_challenge.entity.SendQueue;

public interface SendQueueRepository extends JpaRepository<SendQueue, Long> {
}