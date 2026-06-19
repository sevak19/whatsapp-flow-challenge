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

    public Contact getContact() {
    return contact;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public String getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public void setStatus(String status) {
    this.status = status;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}