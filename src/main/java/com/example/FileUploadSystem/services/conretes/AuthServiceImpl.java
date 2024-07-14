package com.example.FileUploadSystem.services.conretes;

import com.example.FileUploadSystem.core.exceptionhandling.exception.types.BusinessException;
import com.example.FileUploadSystem.core.security.service.JwtService;
import com.example.FileUploadSystem.model.entities.User;
import com.example.FileUploadSystem.repository.UserRepository;
import com.example.FileUploadSystem.services.abstracts.AuthService;
import com.example.FileUploadSystem.services.dtos.request.auth.LoginRequest;
import com.example.FileUploadSystem.services.dtos.request.auth.RegisterRequest;
import com.example.FileUploadSystem.services.dtos.response.auth.RegisterReponse;
import com.example.FileUploadSystem.services.mappers.AuthMapper;
import com.example.FileUploadSystem.services.rules.UserBusinessRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserBusinessRule userBusinessRule;

    @Override
    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()
                -> new BusinessException("Böyle bir e-mail bulunamamıştır."));

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        userBusinessRule.authentication(authentication);

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("UserId", user.getId());
        extraClaims.put("UserEmail", user.getEmail());

        return jwtService.generateToken(user.getUsername(), extraClaims);
    }

    @Override
    public void register(RegisterRequest request) {
        User user = new User();

        userBusinessRule.emailShouldNotExist(request.getEmail());
        userBusinessRule.passwordConfirmation(request);

        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
    }
}
