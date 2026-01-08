package com.demo.api.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        String filePath = path + File.separator + fileName;

        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

        // copy file content to path
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;
    }

    @Override
    public InputStream getResourceFile(String path, String fileName) throws FileNotFoundException {
        Path filePath = Paths.get(path).resolve(fileName);

        // Fix: If running from root project folder, try to find the file in 'demo-api' module
        if (!Files.exists(filePath)) {
            Path modulePath = Paths.get("demo-api").resolve(path).resolve(fileName);
            if (Files.exists(modulePath)) {
                filePath = modulePath;
            }
        }
        return new FileInputStream(filePath.toFile());
    }
}
