package com.example.FileUploadSystem.services.conretes;

import com.example.FileUploadSystem.core.exceptionhandling.exception.types.BusinessException;
import com.example.FileUploadSystem.core.exceptionhandling.exception.types.FileStorageException;
import com.example.FileUploadSystem.model.entities.File;
import com.example.FileUploadSystem.repository.FileRepository;
import com.example.FileUploadSystem.services.abstracts.FileService;
import com.example.FileUploadSystem.services.dtos.request.file.AddFileRequest;
import com.example.FileUploadSystem.services.dtos.request.file.UpdateFileRequest;
import com.example.FileUploadSystem.services.dtos.response.file.AddFileResponse;
import com.example.FileUploadSystem.services.dtos.response.file.DeleteFileResponse;
import com.example.FileUploadSystem.services.dtos.response.file.GetFileResponse;
import com.example.FileUploadSystem.services.dtos.response.file.UpdateFileResponse;
import com.example.FileUploadSystem.services.mappers.FileMapper;
import com.example.FileUploadSystem.services.rules.FileBusinessRule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private FileBusinessRule fileBusinessRule;


    @Override
    public AddFileResponse add(MultipartFile file) {
        Path filePath = fileBusinessRule.uploadFile(file);

        File newFile = new File();
        newFile.setFileName(file.getOriginalFilename());
        newFile.setFilePath(filePath.toString());
        newFile.setUploadDate(LocalDate.now());
        fileRepository.save(newFile);

        return new AddFileResponse(newFile.getFileName(), newFile.getFilePath(), newFile.getUploadDate());
    }

    @Override
    public UpdateFileResponse update(UpdateFileRequest request) {
        File existingFile = fileRepository.findById(request.getId())
                .orElseThrow(()-> new BusinessException("Böyle bir dosya bulunamadı."));
        FileMapper.INSTANCE.fileFromUpdateFileRequest(request,existingFile);
        existingFile = fileRepository.save(existingFile);
        UpdateFileResponse response = FileMapper.INSTANCE.fileFromUpdateFileResponse(existingFile);
        return response;
    }

    @Override
    public DeleteFileResponse delete(long id) {
        File file = fileRepository.findById(id)
                .orElseThrow(()-> new BusinessException("Böyle bir dosya bulunamadı."));
        DeleteFileResponse response = FileMapper.INSTANCE.fileFromDeleteFileResponse(file);
        fileRepository.delete(file);
        return response;
    }

    @Override
    public List<GetFileResponse> getAll() {
        List<File> files = fileRepository.findAll();
        return files.stream().map(f-> new GetFileResponse(f.getId(),f.getFileName(),f.getFilePath(),f.getUploadDate())).toList();
    }

    @Override
    public GetFileResponse getById(long id) {
        File file = fileRepository.findById(id)
                .orElseThrow(()-> new BusinessException("Böyle bir dosya bulunamadı."));
        GetFileResponse response = FileMapper.INSTANCE.fileFromGetFileResponse(file);
        return response;
    }
}
