package com.example.FileUploadSystem.services.dtos.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "E-mail alanı boş olamaz.")
    @Email(message = "Geçerli bir Email değil.")
    private String email;

    @NotBlank(message = "Şifre alanı boş olamaz.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z]).{6,}$"
            ,message = "Şifre en az bir numerik, en az bir tane büyük harf içermeli ve en az 6 karakter olmalıdır.")
    private String password;

    @NotBlank(message = "Şifre alanı boş olamaz.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z]).{6,}$"
            ,message = "Şifre en az bir numerik, en az bir tane büyük harf içermeli ve en az 6 karakter olmalıdır.")
    private String passwordConfirm;
}
