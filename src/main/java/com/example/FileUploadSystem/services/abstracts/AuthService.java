package com.example.FileUploadSystem.services.abstracts;

import com.example.FileUploadSystem.services.dtos.request.auth.LoginRequest;
import com.example.FileUploadSystem.services.dtos.request.auth.RegisterRequest;
import com.example.FileUploadSystem.services.dtos.response.auth.RegisterReponse;

public interface AuthService {
    String login(LoginRequest request);
    void register(RegisterRequest request);
}
