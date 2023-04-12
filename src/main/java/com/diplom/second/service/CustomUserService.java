package com.diplom.second.service;

import com.diplom.second.model.CustomUser;
import com.diplom.second.repository.CustomUserRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserService {
    private final CustomUserRepository customUserRepository;

    public CustomUserService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    @Transactional
    public CustomUser findCustomUserById(Long id) {
        return customUserRepository.findById(id).orElse(null);
    }

    @Transactional
    public CustomUser getByLogin(String login) {
        return customUserRepository.findCustomUserByLogin(login);
    }

    @Transactional
    public boolean saveNewCustomUser(CustomUser customUser) {
        if (customUserRepository.findCustomUserByLogin(customUser.getLogin()) == null) {
            customUserRepository.save(customUser);
            return true;
        }
        return false;
    }

    @Transactional
    public void deleteCustomUserByLogin(String login) {
        if (customUserRepository.findCustomUserByLogin(login) != null) {
            customUserRepository.deleteCustomUserByLogin(login);
        }
    }
}