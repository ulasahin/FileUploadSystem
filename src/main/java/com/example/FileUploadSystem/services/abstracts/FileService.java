package com.example.FileUploadSystem.services.abstracts;

import com.example.FileUploadSystem.services.dtos.request.file.AddFileRequest;
import com.example.FileUploadSystem.services.dtos.request.file.UpdateFileRequest;
import com.example.FileUploadSystem.services.dtos.response.file.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    AddFileResponse add(MultipartFile request);
    UpdateFileResponse update(UpdateFileRequest request);
    DeleteFileResponse delete(long id);
    List<GetFileResponse> getAll();
    GetFileResponse getById(long id);
    List<GetFileResponse> getUserFiles();
}
