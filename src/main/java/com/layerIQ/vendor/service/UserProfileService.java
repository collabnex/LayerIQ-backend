package com.layerIQ.vendor.service;

import com.layerIQ.vendor.model.User;
import com.layerIQ.vendor.model.UserProfile;

public interface UserProfileService {
	UserProfile getMyProfile(User currentUser);
    UserProfile updateMyProfile(User currentUser, UserProfile payload);
}
