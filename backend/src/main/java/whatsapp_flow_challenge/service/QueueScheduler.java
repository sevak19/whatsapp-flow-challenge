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
    private final WppConnectService wppConnectService;

    public QueueScheduler(
            SendQueueRepository sendQueueRepository,
            WppConnectService wppConnectService
    ) {
        this.sendQueueRepository = sendQueueRepository;
        this.wppConnectService = wppConnectService;
    }

    @Scheduled(fixedDelay = 1000)
    public void processQueue() {
        List<SendQueue> pendingMessages =
                sendQueueRepository.findPendingMessages(LocalDateTime.now());

        if (pendingMessages.isEmpty()) return;

        SendQueue message = pendingMessages.get(0);
        try {
            wppConnectService.sendMessage(
                message.getContact().getPhone(),
                message.getMessage()
            );
            message.setStatus("SENT");
            message.setSentAt(LocalDateTime.now());
        } catch (Exception e) {
            message.setStatus("ERROR");
            message.setErrorMessage(e.getMessage());
            System.out.println("[ERRO] " + e.getMessage());
        }
        sendQueueRepository.save(message);
    }
}
