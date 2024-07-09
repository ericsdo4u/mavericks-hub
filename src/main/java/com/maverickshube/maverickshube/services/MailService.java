package com.maverickshube.maverickshube.services;

import com.maverickshube.maverickshube.dtos.request.SendMailRequest;
import org.springframework.stereotype.Service;

@Service
public interface MailService {
    String sendMail(SendMailRequest mailRequest);
}
