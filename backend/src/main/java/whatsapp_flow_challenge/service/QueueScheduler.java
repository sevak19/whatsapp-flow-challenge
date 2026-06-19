package whatsapp_flow_challenge.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import whatsapp_flow_challenge.entity.SendQueue;
import whatsapp_flow_challenge.repository.SendQueueRepository;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class QueueScheduler {

    private final SendQueueRepository sendQueueRepository;

    public QueueScheduler(SendQueueRepository sendQueueRepository) {
        this.sendQueueRepository = sendQueueRepository;
    }

    @Scheduled(fixedDelay = 5000)
    public void processQueue() {

        List<SendQueue> pendingMessages =
                sendQueueRepository.findPendingMessages(LocalDateTime.now());

        /*System.out.println(
                "Mensagens pendentes: "
                + pendingMessages.size()
        );*/

        for (SendQueue message : pendingMessages) {
            try {
            // chamada WPPConnect/mock aqui
            System.out.println("Enviando para: " + message.getContact().getPhone());
            message.setStatus("SENT");                    // <- ponto 2
            message.setSentAt(LocalDateTime.now());
            } catch (Exception e) {
                message.setStatus("ERROR");
                message.setErrorMessage(e.getMessage());
            }
            sendQueueRepository.save(message);
        }
    }
}