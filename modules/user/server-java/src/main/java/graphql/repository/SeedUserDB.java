package graphql.repository;

import graphql.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class SeedUserDB implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) {
        long count = userRepository.count();

        if (count == 0) {
            log.debug("Init DB. Table [USER]");
            userRepository.save(User.builder()
                    .username("username")
                    .role("role")
                    .isActive(true)
                    .email("example@email.com")
                    .profile(UserProfile.builder()
                            .firstName("firstName")
                            .lastName("lastName")
                            .fullName("fullName")
                            .build())
                    .auth(UserAuth.builder()
                            .certificate(CertificateAuth.builder()
                                    .serial(UUID.randomUUID().toString())
                                    .build())
                            .facebook(FacebookAuth.builder()
                                    .fbId(UUID.randomUUID().toString())
                                    .displayName("facebookAuthName")
                                    .build())
                            .github(GithubAuth.builder()
                                    .ghId(UUID.randomUUID().toString())
                                    .displayName("githubAuthName")
                                    .build())
                            .google(GoogleAuth.builder()
                                    .googleId(UUID.randomUUID().toString())
                                    .displayName("googleAuthName")
                                    .build())
                            .linkedin(LinkedInAuth.builder()
                                    .lnId(UUID.randomUUID().toString())
                                    .displayName("linkedinAuthName")
                                    .build())
                            .build())
                    .build());
        }
    }
}