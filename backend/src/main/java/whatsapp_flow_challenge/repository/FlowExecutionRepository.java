package whatsapp_flow_challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whatsapp_flow_challenge.entity.FlowExecution;

public interface FlowExecutionRepository extends JpaRepository<FlowExecution, Long> {
}