package whatsapp_flow_challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whatsapp_flow_challenge.entity.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}