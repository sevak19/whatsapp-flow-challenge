package whatsapp_flow_challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import whatsapp_flow_challenge.entity.FlowExecution;

import java.time.LocalDateTime;
import java.util.List;

public interface FlowExecutionRepository extends JpaRepository<FlowExecution, Long> {

    @Query("""
        SELECT e FROM FlowExecution e
        WHERE e.status = 'PENDING'
        AND e.nextExecutionAt <= :now
        """)
    List<FlowExecution> findPendingExecutions(@Param("now") LocalDateTime now);
}
