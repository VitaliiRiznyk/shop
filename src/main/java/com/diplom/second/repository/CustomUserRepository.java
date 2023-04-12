package com.diplom.second.repository;

import com.diplom.second.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {
    CustomUser findCustomUserByLogin(String login);

    void deleteCustomUserByLogin(String login);
}