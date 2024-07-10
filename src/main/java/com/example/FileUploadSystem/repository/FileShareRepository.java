package com.example.FileUploadSystem.repository;

import com.example.FileUploadSystem.model.entities.FileShare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileShareRepository extends JpaRepository<FileShare,Long> {
}
