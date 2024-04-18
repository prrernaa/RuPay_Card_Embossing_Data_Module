// InactiveUserRepository.java
package com.card.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.card.entity.InactiveUser;

public interface InactiveUserRepository extends JpaRepository<InactiveUser, Long> {
    // No custom methods required
}
