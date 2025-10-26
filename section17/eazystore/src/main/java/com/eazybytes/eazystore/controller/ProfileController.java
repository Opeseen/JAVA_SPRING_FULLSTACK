package com.eazybytes.eazystore.controller;

import com.eazybytes.eazystore.dto.ProfileRequestDTO;
import com.eazybytes.eazystore.dto.ProfileResponseDTO;
import com.eazybytes.eazystore.service.IProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {

  private final IProfileService iProfileService;

  @GetMapping
  public ResponseEntity<ProfileResponseDTO> getProfile(){
    ProfileResponseDTO profileResponseDTO = iProfileService.getProfile();
    return new ResponseEntity<>(profileResponseDTO, HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<ProfileResponseDTO> updateProfile(@Validated @RequestBody ProfileRequestDTO profileRequestDTO){
    ProfileResponseDTO profileResponseDTO = iProfileService.updateProfile(profileRequestDTO);
    return new ResponseEntity<>(profileResponseDTO, HttpStatus.OK);
  }
}
