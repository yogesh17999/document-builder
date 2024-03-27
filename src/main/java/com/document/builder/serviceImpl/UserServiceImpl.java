package com.document.builder.serviceImpl;

import com.document.builder.dto.LoginRequestDto;
import com.document.builder.dto.LoginResponseDto;
import com.document.builder.dto.SignupRequestDto;
import com.document.builder.entity.User;
import com.document.builder.entity.UserRoles;
import com.document.builder.exception.UserValidationException;
import com.document.builder.repository.UserRepository;
import com.document.builder.service.UserService;
import com.document.builder.util.Common;
import com.document.builder.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginResponseDto authenticateUser(LoginRequestDto loginRequestDto) {
        Authentication authentication
                =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(),loginRequestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return LoginResponseDto.builder().token(jwtUtil.generateJwtToken(authentication)).build();
    }

    @Override
    public String addUser(SignupRequestDto signUpRequest) {
        if (Boolean.FALSE.equals(Common.isValidEmail(signUpRequest.getEmail())))
            throw new UserValidationException("Please enter a valid email address");
        if (Boolean.FALSE.equals(Common.isValidPassword(signUpRequest.getPassword())))
            throw new UserValidationException("Please enter a valid password");
        userRepository.findByEmail(signUpRequest.getEmail()).ifPresent(user->{ throw new UserValidationException("User is already exist");}) ;

        User userEntity = modelMapper.map(signUpRequest, User.class);
        userEntity.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        UserRoles userRoles = new UserRoles();
        userRoles.setRoleName(signUpRequest.getRole().getRoleName().toUpperCase());
        userEntity.setRole(userRoles);
        userRepository.save(userEntity);
        return "User Successfully added";
    }
}
