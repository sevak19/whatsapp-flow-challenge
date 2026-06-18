package whatsapp_flow_challenge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "flow_steps")
public class FlowStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flow_id")
    private Flow flow;

    @Column(name = "step_order")
    private Integer stepOrder;

    private String message;

    @Column(name = "delay_minutes")
    private Integer delayMinutes;

    public FlowStep() {
    }

    public Long getId() {
        return id;
    }

    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    public Integer getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(Integer stepOrder) {
        this.stepOrder = stepOrder;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getDelayMinutes() {
        return delayMinutes;
    }

    public void setDelayMinutes(Integer delayMinutes) {
        this.delayMinutes = delayMinutes;
    }
}