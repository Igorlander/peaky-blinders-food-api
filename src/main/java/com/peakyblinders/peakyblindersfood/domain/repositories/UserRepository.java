package com.peakyblinders.peakyblindersfood.domain.repositories;

import com.peakyblinders.peakyblindersfood.domain.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CustomJpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
