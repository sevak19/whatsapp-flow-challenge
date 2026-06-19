package whatsapp_flow_challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "whatsapp_flow_challenge")
@EnableScheduling
public class WhatsappFlowChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhatsappFlowChallengeApplication.class, args);
	}

}
