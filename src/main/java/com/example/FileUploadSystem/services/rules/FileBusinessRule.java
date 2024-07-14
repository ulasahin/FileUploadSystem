package com.example.FileUploadSystem.services.rules;

import com.example.FileUploadSystem.core.exceptionhandling.exception.types.BusinessException;
import com.example.FileUploadSystem.core.exceptionhandling.exception.types.FileStorageException;
import com.example.FileUploadSystem.model.entities.File;
import com.example.FileUploadSystem.repository.FileRepository;
import com.example.FileUploadSystem.services.dtos.response.file.AddFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@Service
public class FileBusinessRule {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    @Value("${file.upload.dir}")
    private String UPLOAD_DIR;

    public Path uploadFile(MultipartFile file){
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        try {

            if (!Files.exists(Paths.get(UPLOAD_DIR))) {
                Files.createDirectories(Paths.get(UPLOAD_DIR));
            }


            Files.write(filePath, file.getBytes());
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();

            throw new BusinessException("Dosya depolanamadı. Hata: " + e.getMessage());
        }
    }
}
