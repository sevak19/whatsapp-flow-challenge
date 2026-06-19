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
    
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getMinDelaySeconds() {
        return minDelaySeconds;
    }

    public void setMinDelaySeconds(Integer minDelaySeconds) {
        this.minDelaySeconds = minDelaySeconds;
    }

    public Integer getMaxDelaySeconds() {
        return maxDelaySeconds;
    }

    public void setMaxDelaySeconds(Integer maxDelaySeconds) {
        this.maxDelaySeconds = maxDelaySeconds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}