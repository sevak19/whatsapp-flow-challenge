package whatsapp_flow_challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import whatsapp_flow_challenge.entity.SendQueue;

import java.time.LocalDateTime;
import java.util.List;

public interface SendQueueRepository extends JpaRepository<SendQueue, Long> {

    @Query("""
        SELECT q
        FROM SendQueue q
        WHERE q.status = 'PENDING'
        AND q.scheduledAt <= :now
        """)
    List<SendQueue> findPendingMessages(@Param("now") LocalDateTime now);
}