package com.example.FileUploadSystem.repository;

import com.example.FileUploadSystem.model.entities.File;
import com.example.FileUploadSystem.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File,Long> {
}
