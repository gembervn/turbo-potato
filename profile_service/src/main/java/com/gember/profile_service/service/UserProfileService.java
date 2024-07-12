package com.gember.profile_service.service;

import org.springframework.stereotype.Service;

import com.gember.profile_service.dto.request.ProfileCreationRequest;
import com.gember.profile_service.dto.response.UserProfileResponse;
import com.gember.profile_service.entity.UserProfile;
import com.gember.profile_service.mapper.UserProfileMapper;
import com.gember.profile_service.repository.UserProfileRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileService {
    UserProfileRepository userProfileRepository;
    UserProfileMapper userProfileMapper;
    
    public UserProfileResponse createProfile(ProfileCreationRequest request) {
        UserProfile userProfile = userProfileMapper.toUserProfile(request);
        userProfile = userProfileRepository.save(userProfile);
        
        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    public UserProfileResponse getProfile(String id) {
        UserProfile userProfile = userProfileRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        return userProfileMapper.toUserProfileResponse(userProfile);
    }
}
