package com.gember.identity_service.configuration;

import java.util.HashSet;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gember.identity_service.constant.PredefinedRole;
import com.gember.identity_service.entity.Role;
import com.gember.identity_service.entity.User;
import com.gember.identity_service.repository.PermissionRepository;
import com.gember.identity_service.repository.RoleRepository;
import com.gember.identity_service.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @NonFinal
    static final String ADMIN_USERNAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin2024";

    @ConditionalOnProperty(prefix = "spring", value = "datasource.driverClassName", havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository) {
        log.info("Initializing application .....");
        return args -> {
            if (userRepository.findByUsername(ADMIN_USERNAME).isEmpty()) {
                roleRepository.save(Role.builder().name(PredefinedRole.USER_ROLE).description("User role").build());
                Role adminRole = roleRepository.save(Role.builder().name(PredefinedRole.ADMIN_ROLE).description("Admin role").build());

                var roles = new HashSet<Role>();
                roles.add(adminRole);

                User user = User.builder().username(ADMIN_USERNAME).password(passwordEncoder.encode(ADMIN_PASSWORD)).roles(roles).build();
                userRepository.save(user);
                log.warn("Admin user has been created with default password: 'admin2024', please change it.");
            }
            log.info("Application initialization completed .....");
        };
    }
}
