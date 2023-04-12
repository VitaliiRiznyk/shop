package com.diplom.second.service;

import com.diplom.second.modelVerification.VerificationCustomUser;
import com.diplom.second.repository.VerificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VerificationService {
    private final VerificationRepository verificationRepository;
    public void addVerificationToken(VerificationCustomUser verificationCustomUser) {
        verificationRepository.save(verificationCustomUser);
    }
    public boolean getVerificationToken(String token) {
        if (verificationRepository.getVerificationCustomUserByToken(token) == null) {
            return false;
        }
        return true;
    }

}