package whatsapp_flow_challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whatsapp_flow_challenge.entity.FlowStep;

import java.util.List;
import java.util.Optional;

public interface FlowStepRepository extends JpaRepository<FlowStep, Long> {

    List<FlowStep> findByFlowIdOrderByStepOrder(Long flowId);

    Optional<FlowStep> findByFlowIdAndStepOrder(Long flowId, Integer stepOrder);
}
