package com.example.FileUploadSystem.controller;

import com.example.FileUploadSystem.services.abstracts.UserService;
import com.example.FileUploadSystem.services.dtos.request.user.AddUserRequest;
import com.example.FileUploadSystem.services.dtos.request.user.UpdateUserRequest;
import com.example.FileUploadSystem.services.dtos.response.user.AddUserReponse;
import com.example.FileUploadSystem.services.dtos.response.user.DeleteUserResponse;
import com.example.FileUploadSystem.services.dtos.response.user.UpdateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public AddUserReponse add(@RequestBody AddUserRequest request){
        return userService.add(request);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public UpdateUserResponse update(@RequestBody UpdateUserRequest request){
        return userService.update(request);
    }
    @DeleteMapping("/delete")
    public DeleteUserResponse delete(@RequestParam long id){
        return userService.delete(id);
    }

}
