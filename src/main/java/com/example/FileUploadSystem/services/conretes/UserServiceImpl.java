package com.example.FileUploadSystem.services.conretes;

import com.example.FileUploadSystem.core.mappers.UserMapper;
import com.example.FileUploadSystem.repository.UserRepository;
import com.example.FileUploadSystem.services.abstracts.UserService;
import com.example.FileUploadSystem.services.dtos.request.user.AddUserRequest;
import com.example.FileUploadSystem.services.dtos.request.user.UpdateUserRequest;
import com.example.FileUploadSystem.services.dtos.response.user.AddUserReponse;
import com.example.FileUploadSystem.services.dtos.response.user.DeleteUserResponse;
import com.example.FileUploadSystem.services.dtos.response.user.UpdateUserResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;


    @Override
    public AddUserReponse add(AddUserRequest request) {
        User user = UserMapper.INSTANCE.requestFromtAddUserRequest(request);

        return null;
    }

    @Override
    public UpdateUserResponse update(UpdateUserRequest request) {
        return null;
    }

    @Override
    public DeleteUserResponse delete(long id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow();
    }
}
