package com.thmocorrencias.model.imagem;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Service
public class FileUploadService {

    @Value("${file.upload.folder}")
    private String fileUploadFolder;

    @Autowired
    private ObjectMapper mapper;

    public File saveOnDisk(MultipartFile webFile1,MultipartFile webFile2, int number) throws Exception {
        Path folderPath = Paths.get(fileUploadFolder);
        Files.createDirectories(folderPath);

        String originalFilename = webFile1.getOriginalFilename();
        String newFilename = "p" + number + originalFilename.substring(originalFilename.lastIndexOf("."));

        Path newFilePath = folderPath.resolve(newFilename);
        Files.copy(webFile1.getInputStream(), newFilePath, StandardCopyOption.REPLACE_EXISTING);

        Files.createDirectories(folderPath);

        String originalFilename2 = webFile2.getOriginalFilename();
        String newFilename2 = "s" + number + originalFilename2.substring(originalFilename2.lastIndexOf("."));

        Path newFilePath2 = folderPath.resolve(newFilename2);
        Files.copy(webFile2.getInputStream(), newFilePath2, StandardCopyOption.REPLACE_EXISTING);

        List<Path> filePaths = new ArrayList<>();
        filePaths.add(newFilePath);
        filePaths.add(newFilePath2);

        String filePathsString = convertPathListToString(filePaths, "\n");

        return new File(filePathsString);
    }

    public String convertPathListToString(List<Path> filePaths, String delimiter) {
        StringJoiner joiner = new StringJoiner(delimiter);
        for (Path path : filePaths) {
            joiner.add(path.toString());
        }
        return joiner.toString();
    }
}