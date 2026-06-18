package whatsapp_flow_challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whatsapp_flow_challenge.entity.FlowStep;

public interface FlowStepRepository extends JpaRepository<FlowStep, Long> {
}