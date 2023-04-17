package com.diplom.second.controller;

import com.diplom.second.model.CustomUser;
import com.diplom.second.model.CustomUserRole;
import com.diplom.second.modelVerification.VerificationCustomUser;
import com.diplom.second.repository.VerificationRepository;
import com.diplom.second.service.CustomUserService;
import com.diplom.second.service.EmailService;
import com.diplom.second.service.VerificationService;
import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class MainRestController {
    private final CustomUserService customUserService;
    private final EmailService emailService;
    private final VerificationService verificationService;
    private final VerificationRepository verificationRepository;

    private final PasswordEncoder passwordEncoder;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/")
    public HttpStatus returnGreeting() {
        return HttpStatus.OK;
    }

    @PermitAll
    @PostMapping("/sign-up")
    public String createNewUser(@RequestBody CustomUser customUser) {
        if (customUserService.getByLogin(customUser.getLogin()) != null) {
            return "Даний логін зайнято";
        }

        CustomUser user = new CustomUser(customUser.getLogin(), passwordEncoder.encode(customUser.getPassword()),
                customUser.getEmail(), CustomUserRole.USER);

        customUserService.saveNewCustomUser(user);

        String token = UUID.randomUUID().toString();
        verificationService.addVerificationToken(new VerificationCustomUser(token, user));
        String email = user.getEmail();
        emailService.sendEmail(email, "Лист підтвердження", "Для підтвердження вашого акаунту" +
                "натисніть наступне посилання https://17042023.azurewebsites.net/confirm?token=" + token);
        return "Користувач зареєстрований, на e-mail адресу надіслано листа верифікації";
    }

    @PermitAll
    @GetMapping("/confirm")
    public String confirmVerification(@RequestParam("token") String token) {
        if (verificationService.getVerificationToken(token)) {

            CustomUser customUser = verificationRepository.getVerificationCustomUserByToken(token).getCustomUser();
            customUser.setVerified(true);
            customUserService.saveNewCustomUser(customUser);

            return "Верифікацію пройдено";
        }
        return "Верифікацію не пройдено";
    }

}