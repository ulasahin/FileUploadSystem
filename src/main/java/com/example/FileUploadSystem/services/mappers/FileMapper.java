package com.example.FileUploadSystem.services.mappers;

import com.example.FileUploadSystem.model.entities.File;
import com.example.FileUploadSystem.services.dtos.request.file.AddFileRequest;
import com.example.FileUploadSystem.services.dtos.request.file.UpdateFileRequest;
import com.example.FileUploadSystem.services.dtos.request.user.AddUserRequest;
import com.example.FileUploadSystem.services.dtos.response.file.AddFileResponse;
import com.example.FileUploadSystem.services.dtos.response.file.DeleteFileResponse;
import com.example.FileUploadSystem.services.dtos.response.file.GetFileResponse;
import com.example.FileUploadSystem.services.dtos.response.file.UpdateFileResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FileMapper {
    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);

    File fileFromAddFileRequest(AddFileRequest request);
    AddFileResponse fileFromAddFileResponse(File file);

    @Mapping(target = "id", ignore = true)
    File fileFromUpdateFileRequest(UpdateFileRequest request, @MappingTarget File file);

    UpdateFileResponse fileFromUpdateFileResponse(File file);

    DeleteFileResponse fileFromDeleteFileResponse(File file);

    GetFileResponse fileFromGetFileResponse(File file);
}
