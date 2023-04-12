package com.diplom.second.configuration;

import com.diplom.second.loggerFilter.CsrfLoggerFilter;
import com.diplom.second.model.*;
import com.diplom.second.service.CustomUserService;
import com.diplom.second.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;
    private final CustomUserService customUserService;
    private final ProductService productService;
    private final PasswordEncoder passwordEncoder;


    /*@Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            CustomUser customUser = new CustomUser("Vital", passwordEncoder.encode("123456"),
                    "a@gmail.com", CustomUserRole.ADMIN);
            customUser.setVerified(true);
            CustomUser customUser1 = new CustomUser("Verona", passwordEncoder.encode("123456"),
                    "a@gmail.com", CustomUserRole.USER);
            customUser1.setVerified(true);
            customUserService.saveNewCustomUser(customUser);

            productService.saveProductToDB(new Product("Iphone", 12_009.8,
                    "Red", ProductBrand.Apple, ProductType.Phone, "512", null));
            productService.saveProductToDB(new Product("AirPods 2", 3500.02, "White",
                    ProductBrand.Apple, ProductType.Headphone, null, null));
            productService.saveProductToDB(new Product("Cable charger", 1000.05, "White",
                    ProductBrand.Apple, ProductType.Accessories, null, null));
            productService.saveProductToDB(new Product("AppleWatch 10", 14454.57, "White",
                    ProductBrand.Apple, ProductType.Watch, "24", null));
        };
    }*/

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilterAfter(new CsrfLoggerFilter(), CsrfFilter.class)
                .authorizeHttpRequests((requests) -> requests.anyRequest().permitAll())
                .formLogin((form) -> form
                        .loginProcessingUrl("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .failureUrl("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());
        return http.build();
    }
}