package com.diplom.second.modelVerification;

import com.diplom.second.model.CustomUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class VerificationCustomUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;
    @OneToOne
    @JoinColumn(name = "user_id")
    private CustomUser customUser;

    public VerificationCustomUser(String token, CustomUser customUser) {
        this.token = token;
        this.customUser = customUser;
    }
}
