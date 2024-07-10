package com.example.FileUploadSystem.core.mappers;

import com.example.FileUploadSystem.model.entities.User;
import com.example.FileUploadSystem.services.dtos.request.user.AddUserRequest;
import com.example.FileUploadSystem.services.dtos.request.user.UpdateUserRequest;
import com.example.FileUploadSystem.services.dtos.response.user.AddUserReponse;
import com.example.FileUploadSystem.services.dtos.response.user.UpdateUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User requestFromtAddUserRequest(AddUserRequest request);
    AddUserReponse responseFromAddUserResponse(User user);

    User requestFromtUpdateUserReponse(UpdateUserRequest request);
    UpdateUserResponse responseFromUpdateUserReponse(User user);
}
