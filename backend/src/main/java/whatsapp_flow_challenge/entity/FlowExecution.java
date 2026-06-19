package whatsapp_flow_challenge.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "flow_executions")
public class FlowExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flow_id")
    private Flow flow;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @Column(name = "current_step")
    private Integer currentStep;

    @Column(name = "next_execution_at")
    private LocalDateTime nextExecutionAt;

    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public FlowExecution() {}

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Flow getFlow() { return flow; }
    public void setFlow(Flow flow) { this.flow = flow; }
    public Contact getContact() { return contact; }
    public void setContact(Contact contact) { this.contact = contact; }
    public Integer getCurrentStep() { return currentStep; }
    public void setCurrentStep(Integer currentStep) { this.currentStep = currentStep; }
    public LocalDateTime getNextExecutionAt() { return nextExecutionAt; }
    public void setNextExecutionAt(LocalDateTime nextExecutionAt) { this.nextExecutionAt = nextExecutionAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
