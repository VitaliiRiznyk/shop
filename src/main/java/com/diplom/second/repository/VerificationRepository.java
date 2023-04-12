package com.diplom.second.repository;

import com.diplom.second.modelVerification.VerificationCustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository<VerificationCustomUser, Long> {
    VerificationCustomUser getVerificationCustomUserByToken(String token);
}