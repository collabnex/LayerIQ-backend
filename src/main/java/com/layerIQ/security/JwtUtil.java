package com.layerIQ.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.layerIQ.vendor.model.User;

public class JwtUtil {

    public static Long getLoggedInUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return null;

        Object principal = auth.getPrincipal();

        if (principal instanceof User user) {
            return user.getId();   // <-- YOU ALREADY HAVE getId()
        }

        return null;
    }
}
