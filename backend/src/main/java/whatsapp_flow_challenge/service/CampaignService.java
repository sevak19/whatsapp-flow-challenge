package whatsapp_flow_challenge.service;

import org.springframework.stereotype.Service;
import whatsapp_flow_challenge.entity.Campaign;
import whatsapp_flow_challenge.entity.Contact;
import whatsapp_flow_challenge.entity.SendQueue;
import whatsapp_flow_challenge.repository.CampaignRepository;
import whatsapp_flow_challenge.repository.ContactRepository;
import whatsapp_flow_challenge.repository.SendQueueRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class CampaignService {

    private final CampaignRepository repository;
    private final ContactRepository contactRepository;
    private final SendQueueRepository sendQueueRepository;
    private final Random random = new Random();

    public CampaignService(
            CampaignRepository repository,
            ContactRepository contactRepository,
            SendQueueRepository sendQueueRepository
    ) {
        this.repository = repository;
        this.contactRepository = contactRepository;
        this.sendQueueRepository = sendQueueRepository;
    }

    public Campaign save(Campaign campaign) {
        return repository.save(campaign);
    }

    public List<Campaign> findAll() {
        return repository.findAll();
    }

    public void dispatch(Long campaignId) {
        Campaign campaign = repository.findById(campaignId)
                .orElseThrow(() -> new RuntimeException("Campanha não encontrada"));

        List<Contact> contacts = contactRepository.findAll();

        for (Contact contact : contacts) {
            int delay = random.nextInt(
                campaign.getMaxDelaySeconds() - campaign.getMinDelaySeconds() + 1
            ) + campaign.getMinDelaySeconds();

            SendQueue queue = new SendQueue();
            queue.setContact(contact);
            queue.setCampaign(campaign);
            queue.setMessage(campaign.getMessage());
            queue.setScheduledAt(LocalDateTime.now().plusSeconds(delay));
            queue.setStatus("PENDING");

            sendQueueRepository.save(queue);
        }
    }
}
