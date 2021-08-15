package com.example.crm.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crm.entity.CrmUser;

@Repository
public interface UserRepository extends JpaRepository<CrmUser, Long> {
    Optional<CrmUser> findByUsername(String username);
}
