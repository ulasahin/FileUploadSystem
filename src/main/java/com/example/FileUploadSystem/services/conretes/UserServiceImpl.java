package com.example.FileUploadSystem.services.conretes;

import com.example.FileUploadSystem.core.exceptionhandling.exception.types.BusinessException;
import com.example.FileUploadSystem.services.mappers.UserMapper;
import com.example.FileUploadSystem.model.entities.User;
import com.example.FileUploadSystem.repository.UserRepository;
import com.example.FileUploadSystem.services.abstracts.UserService;
import com.example.FileUploadSystem.services.dtos.request.user.AddUserRequest;
import com.example.FileUploadSystem.services.dtos.request.user.UpdateUserRequest;
import com.example.FileUploadSystem.services.dtos.response.user.AddUserReponse;
import com.example.FileUploadSystem.services.dtos.response.user.DeleteUserResponse;
import com.example.FileUploadSystem.services.dtos.response.user.UpdateUserResponse;
import com.example.FileUploadSystem.services.rules.UserBusinessRule;
import lombok.RequiredArgsConstructor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserBusinessRule userBusinessRule;

    @Override
    public AddUserReponse add(AddUserRequest request) {
        User user = UserMapper.INSTANCE.userFromtAddUserRequest(request);
        userBusinessRule.emailShouldNotExist(request.getEmail());
        userRepository.save(user);
        AddUserReponse reponse = UserMapper.INSTANCE.userFromAddUserResponse(user);

        return reponse;
    }

    @Override
    public UpdateUserResponse update(UpdateUserRequest request) {
        User existingUser = userRepository.findById(request.getId())
                .orElseThrow(() -> new BusinessException("Güncellenmek istenen kullanıcı bulunamadı!"));

        UserMapper.INSTANCE.userFromtUpdateUserRequest(request,existingUser);
        existingUser = userRepository.save(existingUser);

        UpdateUserResponse updateUserReponse = UserMapper.INSTANCE.userFromUpdateUserReponse(existingUser);
        return updateUserReponse;
    }

    @Override
    public DeleteUserResponse delete(long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new BusinessException("Böyle bir kullanıcı bulunamadı."));
        DeleteUserResponse response = UserMapper.INSTANCE.userFromDeleteResponse(user);
        userRepository.delete(user);
        return response;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow();
    }
}
