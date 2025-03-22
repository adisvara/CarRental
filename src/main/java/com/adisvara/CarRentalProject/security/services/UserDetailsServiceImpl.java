package com.adisvara.CarRentalProject.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adisvara.CarRentalProject.model.User;
import com.adisvara.CarRentalProject.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
    // First check if the provided value is an email (contains @)
    boolean isEmail = usernameOrEmail.contains("@");
    
    User user;
    if (isEmail) {
      // Try to find by email
      user = userRepository.findByEmail(usernameOrEmail)
          .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + usernameOrEmail));
    } else {
      // Try to find by username
      user = userRepository.findByUsername(usernameOrEmail)
          .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + usernameOrEmail));
    }

    return UserDetailsImpl.build(user);
  }
} 