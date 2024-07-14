package com.example.FileUploadSystem.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "files")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;

    private LocalDate uploadDate;

    @OneToMany(mappedBy = "file", cascade = CascadeType.REMOVE)//Bir File silinirse ilgili FileShare kolonuda silinir.
    private List<FileShare> fileShares;
}
