package whatsapp_flow_challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whatsapp_flow_challenge.entity.Flow;

public interface FlowRepository extends JpaRepository<Flow, Long> {

}