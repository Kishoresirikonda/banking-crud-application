package com.kishore.banking_app;

import com.kishore.banking_app.entity.Users;
import com.kishore.banking_app.repository.UserDetailsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BankingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingAppApplication.class, args);
	}

    @Bean
    public CommandLineRunner createAdminUser(UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder){
        return args -> {
            if(userDetailsRepository.findByUsername("Kishore").isEmpty()){
                Users admin = new Users();
                admin.setUsername("Kishore");
                admin.setPassword(passwordEncoder.encode("kishore1234"));
                admin.setRole("ROLE_ADMIN");

                userDetailsRepository.save(admin);
                System.out.println("Admin Created");
            }
        };
    }
}
