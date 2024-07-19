package com.example.FileUploadSystem.services.conretes;

import com.example.FileUploadSystem.core.exceptionhandling.exception.problemdetails.ErrorMessages;
import com.example.FileUploadSystem.core.exceptionhandling.exception.types.BusinessException;
import com.example.FileUploadSystem.model.entities.File;
import com.example.FileUploadSystem.model.entities.FileShare;
import com.example.FileUploadSystem.model.entities.User;
import com.example.FileUploadSystem.repository.FileRepository;
import com.example.FileUploadSystem.repository.FileShareRepository;
import com.example.FileUploadSystem.repository.UserRepository;
import com.example.FileUploadSystem.services.abstracts.FileService;
import com.example.FileUploadSystem.services.dtos.request.file.UpdateFileRequest;
import com.example.FileUploadSystem.services.dtos.response.file.*;
import com.example.FileUploadSystem.services.mappers.FileMapper;
import com.example.FileUploadSystem.services.rules.FileBusinessRule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private FileBusinessRule fileBusinessRule;
    @Autowired
    private FileShareRepository fileShareRepository;

    @Value("${file.upload.dir}")
    private String UPLOAD_DIR;

    @Override
    public AddFileResponse add(MultipartFile file) {

        User user = fileBusinessRule.authentication();
        Path filePath = fileBusinessRule.uploadFile(file,user.getId());

        File newFile = new File();
        newFile.setFileName(file.getOriginalFilename());
        newFile.setFilePath(filePath.toString());
        newFile.setUploadDate(LocalDate.now());
        fileRepository.save(newFile);
        FileShare fileShare = new FileShare();
        fileShare.setFile(newFile);
        fileShare.setUser(user);
        fileShareRepository.save(fileShare);


        return new AddFileResponse(newFile.getFileName(), newFile.getFilePath(), newFile.getUploadDate());
    }

    @Override
    public UpdateFileResponse update(UpdateFileRequest request) {
        File existingFile = fileRepository.findById(request.getId())
                .orElseThrow(()-> new BusinessException(ErrorMessages.FILE_NOT_FOUND));
        FileMapper.INSTANCE.fileFromUpdateFileRequest(request,existingFile);
        existingFile = fileRepository.save(existingFile);
        UpdateFileResponse response = FileMapper.INSTANCE.fileFromUpdateFileResponse(existingFile);
        return response;
    }

    @Override
    public DeleteFileResponse delete(long id) {
        File file = fileRepository.findById(id)
                .orElseThrow(()-> new BusinessException(ErrorMessages.FILE_NOT_FOUND));

        Path filePath = Paths.get(file.getFilePath());
        fileBusinessRule.deleteFile(filePath);

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
                .orElseThrow(()-> new BusinessException(ErrorMessages.FILE_NOT_FOUND));
        GetFileResponse response = FileMapper.INSTANCE.fileFromGetFileResponse(file);
        return response;
    }

    @Override
    public List<GetFileResponse> getUserFiles() {
        User user = fileBusinessRule.authentication();
        Long userId = user.getId();
        return fileShareRepository.findByUserId(userId).stream()
                .map(FileShare::getFile)
                .map(f -> new GetFileResponse(f.getId(),f.getFileName()
                        ,f.getFilePath(),f.getUploadDate()))
                .collect(Collectors.toList());
    }

    @Override
    public byte[] dowloadFile(long id) throws IOException{

        File file = fileRepository.findById(id).orElseThrow();
        Path path = Path.of(file.getFilePath());

        return Files.readAllBytes(path);
    }
}
