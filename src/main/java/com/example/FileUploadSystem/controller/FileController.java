package com.example.FileUploadSystem.controller;

import com.example.FileUploadSystem.services.abstracts.FileService;
import com.example.FileUploadSystem.services.dtos.request.file.UpdateFileRequest;
import com.example.FileUploadSystem.services.dtos.response.file.AddFileResponse;
import com.example.FileUploadSystem.services.dtos.response.file.DeleteFileResponse;
import com.example.FileUploadSystem.services.dtos.response.file.GetFileResponse;
import com.example.FileUploadSystem.services.dtos.response.file.UpdateFileResponse;
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
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<GetFileResponse> getAll(){
        return fileService.getAll();
    }
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetFileResponse getById(@RequestParam long id){
        return fileService.getById(id);
    }
    @DeleteMapping("/delete")
    public DeleteFileResponse delete(@RequestParam long id){
        return fileService.delete(id);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public UpdateFileResponse update(@RequestBody UpdateFileRequest request){
        return fileService.update(request);
    }


}
