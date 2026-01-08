package com.layerIQ.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.layerIQ.vendor.model.UserProfile;

import java.util.Optional;
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUserId(Long userId);
}
