package com.example.FileUploadSystem.repository;

import com.example.FileUploadSystem.model.entities.FileShare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileShareRepository extends JpaRepository<FileShare,Long> {
    List<FileShare> findByUserId(Long userId);
}
