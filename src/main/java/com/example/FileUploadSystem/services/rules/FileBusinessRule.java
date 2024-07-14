package com.example.FileUploadSystem.services.rules;

import com.example.FileUploadSystem.core.exceptionhandling.exception.types.BusinessException;
import com.example.FileUploadSystem.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class FileBusinessRule {
    @Autowired
    private FileRepository fileRepository;

    @Value("${file.upload.dir}")
    private String UPLOAD_DIR;

    public Path uploadFile(MultipartFile file,long userId){
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + userId + "/" + fileName);

        try {

            if (!Files.exists(Paths.get(UPLOAD_DIR + userId))) {
                Files.createDirectories(Paths.get(UPLOAD_DIR + userId));
            }

            Files.write(filePath, file.getBytes());
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();

            throw new BusinessException("Dosya depolanamadı. Hata: " + e.getMessage());
        }
    }
    public void deleteFile(Path filePath){
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("Dosya fiziksel olarak silinemedi. Hata: " + e.getMessage());
        }
    }
}
