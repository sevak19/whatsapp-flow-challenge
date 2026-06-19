package whatsapp_flow_challenge.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import whatsapp_flow_challenge.service.FlowService;

@Component
public class FlowScheduler {

    private final FlowService flowService;

    public FlowScheduler(FlowService flowService) {
        this.flowService = flowService;
    }

    @Scheduled(fixedDelay = 5000)
    public void processPendingFlows() {
        flowService.processPendingExecutions();
    }
}
