package com.example.FileUploadSystem.repository;

import com.example.FileUploadSystem.model.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File,Long> {
}
