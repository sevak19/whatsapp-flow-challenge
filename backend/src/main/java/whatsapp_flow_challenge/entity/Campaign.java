package whatsapp_flow_challenge.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "campaigns")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String message;

    @Column(name = "min_delay_seconds")
    private Integer minDelaySeconds;

    @Column(name = "max_delay_seconds")
    private Integer maxDelaySeconds;

    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Campaign() {
    }

    public Long getId() {
        return id;
    }
}