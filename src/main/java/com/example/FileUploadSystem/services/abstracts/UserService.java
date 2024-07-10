package com.example.FileUploadSystem.services.abstracts;

import com.example.FileUploadSystem.services.dtos.request.user.AddUserRequest;
import com.example.FileUploadSystem.services.dtos.request.user.UpdateUserRequest;
import com.example.FileUploadSystem.services.dtos.response.user.AddUserReponse;
import com.example.FileUploadSystem.services.dtos.response.user.DeleteUserResponse;
import com.example.FileUploadSystem.services.dtos.response.user.UpdateUserResponse;

public interface UserService  {
    AddUserReponse add(AddUserRequest request);
    UpdateUserResponse update(UpdateUserRequest request);
    DeleteUserResponse delete(long id);
}
