package com.example.FileUploadSystem.services.mappers;

import com.example.FileUploadSystem.model.entities.User;
import com.example.FileUploadSystem.services.dtos.request.user.AddUserRequest;
import com.example.FileUploadSystem.services.dtos.request.user.UpdateUserRequest;
import com.example.FileUploadSystem.services.dtos.response.user.AddUserReponse;
import com.example.FileUploadSystem.services.dtos.response.user.DeleteUserResponse;
import com.example.FileUploadSystem.services.dtos.response.user.UpdateUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userFromtAddUserRequest(AddUserRequest request);
    AddUserReponse userFromAddUserResponse(User user);

    @Mapping(target = "id", ignore = true)
    User userFromtUpdateUserRequest(UpdateUserRequest request, @MappingTarget User user);

    UpdateUserResponse userFromUpdateUserReponse(User user);

    DeleteUserResponse userFromDeleteResponse(User user);
}
