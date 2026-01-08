package com.layerIQ.vendor.service;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.layerIQ.vendor.model.User;
public interface UserService extends UserDetailsService {
    User registerLocal(String fullName, String email, String rawPassword);
    User getById(Long id);
    User getByEmail(String email);
}
