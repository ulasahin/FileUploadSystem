package com.example.FileUploadSystem.core.mappers;

import com.example.FileUploadSystem.model.entities.User;
import com.example.FileUploadSystem.services.dtos.request.auth.LoginRequest;
import com.example.FileUploadSystem.services.dtos.request.auth.RegisterRequest;
import com.example.FileUploadSystem.services.dtos.response.auth.LoginResponse;
import com.example.FileUploadSystem.services.dtos.response.auth.RegisterReponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthMapper {
    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    RegisterReponse responseFromRegisterResponse(User user);
    User requestFromRegisterRequest(RegisterRequest request);

    LoginResponse responseFromLoginReponse(User user);
    User requestFromLoginRequest(LoginRequest request);
}
