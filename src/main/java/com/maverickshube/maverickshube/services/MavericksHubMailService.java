package com.maverickshube.maverickshube.services;

import com.maverickshube.maverickshube.config.MailConfig;
import com.maverickshube.maverickshube.dtos.request.BrevoMailRequest;
import com.maverickshube.maverickshube.dtos.request.Recipient;
import com.maverickshube.maverickshube.dtos.request.SendMailRequest;
import com.maverickshube.maverickshube.dtos.request.Sender;
import com.maverickshube.maverickshube.dtos.response.BravoMailResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;


@AllArgsConstructor
@Service
public class MavericksHubMailService implements  MailService {

    private final MailConfig mailConfig;

    @Override
    public String sendMail(SendMailRequest mailRequest) {
        RestTemplate restTemplate = new RestTemplate();
        String url = mailConfig.getMailApiUrl();
        BrevoMailRequest request = new BrevoMailRequest();
        request.setSubject(mailRequest.getSubject());
        request.setSender(new Sender());
        request.setRecipients(List.of(new Recipient(mailRequest.getEmail(), mailRequest.getRecipientName())));
        request.setContent(mailRequest.getContent());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", mailConfig.getMailApikey());
        headers.set("accept", MediaType.APPLICATION_JSON.toString());
        RequestEntity<?> httpRequest = new RequestEntity<>(request, headers, HttpMethod.POST, URI.create(url));
        ResponseEntity<BravoMailResponse> response = restTemplate.postForEntity(url, httpRequest, BravoMailResponse.class);
        if (response.getBody() != null && response.getStatusCode() == HttpStatusCode.valueOf(201))
            return "mail sent successfully";
        else throw new RuntimeException("Error sending mail");
    }
}