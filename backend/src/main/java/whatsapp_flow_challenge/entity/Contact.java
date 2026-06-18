package whatsapp_flow_challenge.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Contact() {
    }

    // getters e setters
}