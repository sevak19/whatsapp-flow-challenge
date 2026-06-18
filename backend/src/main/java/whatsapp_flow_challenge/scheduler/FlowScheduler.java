package whatsapp_flow_challenge.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FlowScheduler {

    @Scheduled(fixedDelay = 5000)
    public void processPendingFlows() {

        System.out.println("Scheduler executando...");

    }
}