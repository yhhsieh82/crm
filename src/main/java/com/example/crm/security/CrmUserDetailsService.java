package com.example.crm.security;

import java.util.HashSet;
import java.util.Set;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.crm.entity.CrmUser;
import com.example.crm.repository.UserRepository;

public class CrmUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CrmUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final CrmUser crmUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("username: %s doe not exist.", username)));

        final Set<GrantedAuthority> authoritySet = new HashSet<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + crmUser.getRole());
        authoritySet.add(grantedAuthority);
        return new CrmUserDetails(crmUser.getUsername(), crmUser.getPassword(), authoritySet);
    }
}
