package com.example.FileUploadSystem.services.rules;

import com.example.FileUploadSystem.core.exceptionhandling.exception.problemdetails.ErrorMessages;
import com.example.FileUploadSystem.core.exceptionhandling.exception.types.BusinessException;
import com.example.FileUploadSystem.model.entities.User;
import com.example.FileUploadSystem.repository.UserRepository;
import com.example.FileUploadSystem.services.dtos.request.auth.LoginRequest;
import com.example.FileUploadSystem.services.dtos.request.auth.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserBusinessRule {
    @Autowired
    private UserRepository userRepository;

    public void emailShouldNotExist(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()){
            throw new BusinessException(ErrorMessages.EMAİL_USED);
        }
    }
    public void authentication(Authentication authentication){
        if(!authentication.isAuthenticated()){
            throw new BusinessException(ErrorMessages.INVALID_PASS_EMAİL);
        }
    }
    public void passwordConfirmation(RegisterRequest request){
        if (!(request.getPassword().equals(request.getPasswordConfirm()))){
            throw new BusinessException(ErrorMessages.NOT_SAME_PASS);
        }
    }
}
