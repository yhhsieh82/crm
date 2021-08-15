package com.example.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.crm.entity.CrmUser;
import com.example.crm.repository.UserRepository;

@Service
public class CrmUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CrmUserService(@Autowired final UserRepository userRepository, @Autowired final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CrmUser createUser(final CrmUser user) {
        final CrmUser crmUser = new CrmUser();
        crmUser.setEmail(user.getEmail());
        crmUser.setUsername(user.getUsername());
        crmUser.setPassword(passwordEncoder.encode(user.getPassword()));
        crmUser.setRole(user.getRole());
        return userRepository.save(crmUser);
    }
}
