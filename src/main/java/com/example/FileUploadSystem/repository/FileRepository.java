package com.example.FileUploadSystem.repository;

import com.example.FileUploadSystem.model.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FileRepository extends JpaRepository<File,Long> {
}
