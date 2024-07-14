package com.example.FileUploadSystem.controller;

import com.example.FileUploadSystem.services.abstracts.FileService;
import com.example.FileUploadSystem.services.dtos.response.file.AddFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    public AddFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        return fileService.add(file);
    }
}
