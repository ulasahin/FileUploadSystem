package com.example.FileUploadSystem.controller;

import com.example.FileUploadSystem.services.abstracts.FileService;
import com.example.FileUploadSystem.services.dtos.response.file.AddFileResponse;
import com.example.FileUploadSystem.services.dtos.response.file.DeleteFileResponse;
import com.example.FileUploadSystem.services.dtos.response.file.GetFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public AddFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        return fileService.add(file);
    }
    @GetMapping("/getFiles")
    @ResponseStatus(HttpStatus.OK)
    public List<GetFileResponse> getUserFiles(){
        return fileService.getUserFiles();
    }
    @DeleteMapping("/delete")
    public DeleteFileResponse delete(long id){
        return fileService.delete(id);
    }

}
