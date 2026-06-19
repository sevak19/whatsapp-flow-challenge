package whatsapp_flow_challenge.service;

import org.springframework.stereotype.Service;
import whatsapp_flow_challenge.entity.*;
import whatsapp_flow_challenge.repository.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlowService {

    private final FlowRepository flowRepository;
    private final FlowStepRepository flowStepRepository;
    private final FlowExecutionRepository flowExecutionRepository;
    private final ContactRepository contactRepository;
    private final SendQueueRepository sendQueueRepository;

    public FlowService(
            FlowRepository flowRepository,
            FlowStepRepository flowStepRepository,
            FlowExecutionRepository flowExecutionRepository,
            ContactRepository contactRepository,
            SendQueueRepository sendQueueRepository
    ) {
        this.flowRepository = flowRepository;
        this.flowStepRepository = flowStepRepository;
        this.flowExecutionRepository = flowExecutionRepository;
        this.contactRepository = contactRepository;
        this.sendQueueRepository = sendQueueRepository;
    }

    public Flow save(Flow flow) {
        return flowRepository.save(flow);
    }

    public void addContactsToFlow(Long flowId) {
        Flow flow = flowRepository.findById(flowId)
                .orElseThrow(() -> new RuntimeException("Fluxo não encontrado"));

        List<FlowStep> steps = flowStepRepository.findByFlowIdOrderByStepOrder(flowId);
        if (steps.isEmpty()) throw new RuntimeException("Fluxo sem etapas");

        List<Contact> contacts = contactRepository.findAll();

        for (Contact contact : contacts) {
            FlowExecution execution = new FlowExecution();
            execution.setFlow(flow);
            execution.setContact(contact);
            execution.setCurrentStep(1);
            execution.setNextExecutionAt(LocalDateTime.now());
            execution.setStatus("PENDING");
            flowExecutionRepository.save(execution);
            System.out.println("[FLUXO] Contato " + contact.getName() + " adicionado ao fluxo '" + flow.getName() + "'");
        }
    }

    public void processPendingExecutions() {
        List<FlowExecution> executions =
                flowExecutionRepository.findPendingExecutions(LocalDateTime.now());

        for (FlowExecution execution : executions) {
            Long flowId = execution.getFlow().getId();
            Integer currentStep = execution.getCurrentStep();
            String contactName = execution.getContact().getName();
            String contactPhone = execution.getContact().getPhone();

            flowStepRepository
                .findByFlowIdAndStepOrder(flowId, currentStep)
                .ifPresent(step -> {
                    SendQueue queue = new SendQueue();
                    queue.setContact(execution.getContact());
                    queue.setMessage(step.getMessage());
                    queue.setScheduledAt(LocalDateTime.now());
                    queue.setStatus("PENDING");
                    sendQueueRepository.save(queue);

                    System.out.println("[FLUXO] Etapa " + currentStep + " enfileirada para " + contactName + " (" + contactPhone + "): \"" + step.getMessage() + "\"");

                    flowStepRepository
                        .findByFlowIdAndStepOrder(flowId, currentStep + 1)
                        .ifPresentOrElse(
                            nextStep -> {
                                execution.setCurrentStep(currentStep + 1);
                                execution.setNextExecutionAt(
                                    LocalDateTime.now().plusMinutes(nextStep.getDelayMinutes())
                                );
                                System.out.println("[FLUXO] Próxima etapa em " + nextStep.getDelayMinutes() + " minuto(s)");
                            },
                            () -> {
                                execution.setStatus("COMPLETED");
                                System.out.println("[FLUXO] Fluxo concluído para " + contactName);
                            }
                        );

                    flowExecutionRepository.save(execution);
                });
        }
    }
}
