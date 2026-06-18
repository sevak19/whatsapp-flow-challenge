package whatsapp_flow_challenge.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "send_queue")
public class SendQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    private String message;

    @Column(name = "scheduled_at")
    private LocalDateTime scheduledAt;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    private String status;

    @Column(name = "error_message")
    private String errorMessage;

    public SendQueue() {
    }

    public Long getId() {
        return id;
    }
}