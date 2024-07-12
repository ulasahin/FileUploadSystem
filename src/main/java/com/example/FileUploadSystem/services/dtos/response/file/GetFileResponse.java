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
public class GetFileResponse {
    private long id;
    private String fileName;
    private String filePath;
    private LocalDate uploadDate;
}
