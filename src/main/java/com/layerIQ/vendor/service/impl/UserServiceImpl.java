package com.layerIQ.vendor.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.layerIQ.exceptions.BusinessException;
import com.layerIQ.exceptions.NotFoundException;
import com.layerIQ.vendor.model.User;
import com.layerIQ.vendor.model.UserProfile;
import com.layerIQ.vendor.model.UserRole;
import com.layerIQ.vendor.repository.UserProfileRepository;
import com.layerIQ.vendor.repository.UserRepository;
import com.layerIQ.vendor.service.UserService;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerLocal(String fullName, String email, String rawPassword) {
        if (userRepository.existsByEmailIgnoreCase(email)) {
            throw new BusinessException("Email already registered");
        }
        User user = User.builder()
                .email(email)
                .passwordHash(passwordEncoder.encode(rawPassword))
                .role(UserRole.USER)
                .active(true)
                .build();
        user = userRepository.save(user);
        UserProfile profile = UserProfile.builder()
                .user(user).fullName(fullName).build();
        profileRepository.save(profile);
        return user;
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmailIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}


