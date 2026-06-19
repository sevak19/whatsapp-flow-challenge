package whatsapp_flow_challenge.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class WppConnectService {

    @Value("${wppconnect.url}")
    private String url;

    @Value("${wppconnect.token}")
    private String token;

    @Value("${wppconnect.session}")
    private String session;

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendMessage(String phone, String message) {
        try {
            String endpoint = url + "/api/" + session + "/send-message";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(token);

            Map<String, String> body = Map.of(
                "phone", phone + "@c.us",
                "message", message
            );

            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
            restTemplate.postForEntity(endpoint, request, String.class);

            System.out.println("[WPP] Enviado para " + phone + ": \"" + message + "\"");
        } catch (Exception e) {
            System.out.println("[WPP-MOCK] " + phone + ": \"" + message + "\"");
        }
    }
}
