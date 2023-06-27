package com.thmocorrencias.webService;

import com.thmocorrencias.model.imagem.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/upimagem")
public class ImageUploadController {

    @Autowired
    private FileUploadService service;

    @PostMapping(path = "/save-on-disk", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String saveOnDisk(@RequestPart("file1") MultipartFile uploadFile1, @RequestPart("file2") MultipartFile uploadFile2, @RequestParam("number") int number) {
        try {
            File newFile = service.saveOnDisk(uploadFile1, uploadFile2, number);
            return newFile.getAbsolutePath();
        } catch (Exception e) {
            // TODO: Refactor Exception Handler
            e.printStackTrace();
            return null;
        }
    }


    private static final String IMAGES_FOLDER = "imagens";

        @GetMapping(path = "/view-from-disk/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<String>> viewFromDisk(@PathVariable("id") Integer id) {
            try {
                String pImagePath = getImagePathById(id, "p");
                String sImagePath = getImagePathById(id, "s");

                List<String> imageURLs = new ArrayList<>();

                if (pImagePath != null && sImagePath != null) {
                    imageURLs.add("imagens/" + pImagePath);
                    imageURLs.add("imagens/" + sImagePath);

                    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(imageURLs);
                } else {
                    return ResponseEntity.notFound().build();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private String getImagePathById(int id, String prefix) throws IOException {
            String number = String.valueOf(Math.abs(id));
            String targetFileName = prefix + number + ".jpg";
            Path imagePath = Paths.get(IMAGES_FOLDER, targetFileName);

            if (Files.exists(imagePath)) {
                return targetFileName;
            } else {
                return null;
            }
        }

}


