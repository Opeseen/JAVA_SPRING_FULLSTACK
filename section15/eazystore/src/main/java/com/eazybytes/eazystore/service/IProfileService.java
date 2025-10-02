package com.eazybytes.eazystore.service;

import com.eazybytes.eazystore.dto.ProfileRequestDTO;
import com.eazybytes.eazystore.dto.ProfileResponseDTO;

public interface IProfileService {
  ProfileResponseDTO getProfile();
  ProfileResponseDTO updateProfile(ProfileRequestDTO profileRequestDTO);
}
