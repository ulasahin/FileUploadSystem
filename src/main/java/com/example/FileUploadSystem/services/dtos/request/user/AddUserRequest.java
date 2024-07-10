package com.example.FileUploadSystem.services.dtos.request.user;

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
public class AddUserRequest {
    @NotBlank(message = "E-mail kısmı boş olamaz")
    @Email(message = "Geçerli bir e-mail giriniz.")
    private String email;

    @NotBlank(message = "Şifre alanı boş olamaz.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z]).{6,}$"
            ,message = "Şifre en az bir numerik, en az bir tane büyük harf içermeli ve en az 6 karakter olmalıdır.")
    private String password;
}

