package com.example.FileUploadSystem.services.abstracts;

import com.example.FileUploadSystem.services.dtos.request.file.AddFileRequest;
import com.example.FileUploadSystem.services.dtos.request.file.UpdateFileRequest;
import com.example.FileUploadSystem.services.dtos.response.file.AddFileResponse;
import com.example.FileUploadSystem.services.dtos.response.file.DeleteFileResponse;
import com.example.FileUploadSystem.services.dtos.response.file.GetFileResponse;
import com.example.FileUploadSystem.services.dtos.response.file.UpdateFileResponse;

import java.util.List;

public interface FileService {
    AddFileResponse add(AddFileRequest request);
    UpdateFileResponse update(UpdateFileRequest request);
    DeleteFileResponse delete(long id);
    List<GetFileResponse> getAll();
    GetFileResponse getById(long id);
}
