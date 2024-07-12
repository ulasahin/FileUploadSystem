package com.example.FileUploadSystem.services.dtos.response.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFileResponse {
    private String fileName;
    private String filePath;
    private LocalDate uploadDate;
}
