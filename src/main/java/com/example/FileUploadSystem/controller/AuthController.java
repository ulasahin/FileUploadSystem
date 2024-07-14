package com.example.FileUploadSystem.controller;

import com.example.FileUploadSystem.services.abstracts.AuthService;
import com.example.FileUploadSystem.services.dtos.request.auth.LoginRequest;
import com.example.FileUploadSystem.services.dtos.request.auth.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public String login(@RequestBody LoginRequest request){
        return authService.login(request);
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterRequest request){authService.register(request);
    }
}
