package whatsapp_flow_challenge.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import whatsapp_flow_challenge.entity.SendQueue;
import whatsapp_flow_challenge.repository.SendQueueRepository;

@Service
public class SendQueueService {

    private final SendQueueRepository repository;

    public SendQueueService(SendQueueRepository repository) {
        this.repository = repository;
    }

    public SendQueue save(SendQueue sendQueue) {
       if (sendQueue.getScheduledAt() == null) {
            sendQueue.setScheduledAt(LocalDateTime.now());
        }
        return repository.save(sendQueue);
    }
}