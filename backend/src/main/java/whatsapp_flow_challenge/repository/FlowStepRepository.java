package whatsapp_flow_challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import whatsapp_flow_challenge.entity.FlowStep;

import java.util.List;
import java.util.Optional;

public interface FlowStepRepository extends JpaRepository<FlowStep, Long> {

    List<FlowStep> findByFlowIdOrderByStepOrder(Long flowId);

    @Query("SELECT s FROM FlowStep s WHERE s.flow.id = :flowId AND s.stepOrder = :stepOrder")
    List<FlowStep> findStepsByFlowAndOrder(@Param("flowId") Long flowId, @Param("stepOrder") Integer stepOrder);
}
