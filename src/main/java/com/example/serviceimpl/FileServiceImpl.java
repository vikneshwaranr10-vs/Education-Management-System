package com.example.serviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.exception.ResourceNotFoundException;
import com.example.service.FileService;

@Service
public class FileServiceImpl implements FileService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public String uploadFile(MultipartFile file) {

        // Check empty file
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }

        try {
            Path uploadPath = Paths.get(uploadDir)
                    .toAbsolutePath()
                    .normalize();

            // Create uploads directory if not exists
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Get original file name
            String fileName = file.getOriginalFilename();

            if (fileName == null || fileName.isBlank()) {
                throw new IllegalArgumentException("Invalid file name");
            }

            // Remove unsafe path information
            fileName = Paths.get(fileName)
                    .getFileName()
                    .toString();

            Path filePath = uploadPath
                    .resolve(fileName)
                    .normalize();

            // Path traversal protection
            if (!filePath.startsWith(uploadPath)) {
                throw new IllegalArgumentException(
                        "Invalid file path"
                );
            }

            // Copy file
            Files.copy(
                    file.getInputStream(),
                    filePath
            );

            return fileName;

        } catch (IOException e) {
            throw new RuntimeException(
                    "File upload failed", e
            );
        }
    }

    @Override
    public byte[] downloadFile(String fileName) {

        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException(
                    "File name is required"
            );
        }

        try {
            Path uploadPath = Paths.get(uploadDir)
                    .toAbsolutePath()
                    .normalize();

            // Remove unsafe path information
            fileName = Paths.get(fileName)
                    .getFileName()
                    .toString();

            Path filePath = uploadPath
                    .resolve(fileName)
                    .normalize();

            // Path traversal protection
            if (!filePath.startsWith(uploadPath)) {
                throw new IllegalArgumentException(
                        "Invalid file path"
                );
            }

            // Check file exists
            if (!Files.exists(filePath)
                    || !Files.isRegularFile(filePath)) {

                throw new ResourceNotFoundException(
                        "File not found: " + fileName
                );
            }

            return Files.readAllBytes(filePath);

        } catch (IOException e) {
            throw new RuntimeException(
                    "File download failed", e
            );
        }
    }
}