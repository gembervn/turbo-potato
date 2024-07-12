package com.gember.profile_service.mapper;

import org.mapstruct.Mapper;

import com.gember.profile_service.dto.request.ProfileCreationRequest;
import com.gember.profile_service.dto.response.UserProfileResponse;
import com.gember.profile_service.entity.UserProfile;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(ProfileCreationRequest request);
    UserProfileResponse toUserProfileResponse(UserProfile entity);
}
