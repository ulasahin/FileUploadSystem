package com.example.FileUploadSystem.services.rules;

import com.example.FileUploadSystem.core.exceptionhandling.exception.types.BusinessException;
import com.example.FileUploadSystem.model.entities.User;
import com.example.FileUploadSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserBusinessRule {
    @Autowired
    private UserRepository userRepository;

    public void emailShouldNotExist(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()){
            throw new BusinessException("Bu email daha önce kullanışmıştır.");
        }
    }
}
