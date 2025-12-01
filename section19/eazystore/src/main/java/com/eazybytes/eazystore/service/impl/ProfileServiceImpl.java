package com.eazybytes.eazystore.service.impl;

import com.eazybytes.eazystore.dto.AddressDTO;
import com.eazybytes.eazystore.dto.ProfileRequestDTO;
import com.eazybytes.eazystore.dto.ProfileResponseDTO;
import com.eazybytes.eazystore.entity.Address;
import com.eazybytes.eazystore.entity.Customer;
import com.eazybytes.eazystore.repository.CustomerRepository;
import com.eazybytes.eazystore.service.IProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements IProfileService {
  private final CustomerRepository customerRepository;
  @Override
  public ProfileResponseDTO getProfile(){
    Customer customer = getAuthenticatedCustomer();
    return mapCustomerToProfileResponseDTO(customer);
  }

  @Override
  public ProfileResponseDTO updateProfile(ProfileRequestDTO profileRequestDTO) {
    Customer customer = getAuthenticatedCustomer();
    boolean isEmailUpdated = !customer.getEmail().equals(profileRequestDTO.getEmail().trim());
    BeanUtils.copyProperties(profileRequestDTO, customer);
    Address address = customer.getAddress();
    if (address == null) {
      address = new Address();
      address.setCustomer(customer);
    }
    address.setStreet(profileRequestDTO.getStreet());
    address.setCity(profileRequestDTO.getCity());
    address.setState(profileRequestDTO.getState());
    address.setPostalCode(profileRequestDTO.getPostalCode());
    address.setCountry(profileRequestDTO.getCountry());
    customer.setAddress(address);
    customer = customerRepository.save(customer);
    ProfileResponseDTO profileResponseDto = mapCustomerToProfileResponseDTO(customer);
    profileResponseDto.setEmailUpdated(isEmailUpdated);
    return profileResponseDto;
  }

  public Customer getAuthenticatedCustomer(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();
    return customerRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  private ProfileResponseDTO mapCustomerToProfileResponseDTO(Customer customer){
    ProfileResponseDTO profileResponseDTO = new ProfileResponseDTO();
    BeanUtils.copyProperties(customer, profileResponseDTO);
    if (customer.getAddress() != null) {
      AddressDTO addressDTO = new AddressDTO();
      BeanUtils.copyProperties(customer.getAddress(), addressDTO);
      profileResponseDTO.setAddress(addressDTO);
    }
    return profileResponseDTO;
  }
}
