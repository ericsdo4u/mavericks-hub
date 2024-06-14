package com.maverickshube.maverickshube.services;

import org.springframework.stereotype.Service;

@Service
public interface MailService {

    String sendMail(String email);
}
