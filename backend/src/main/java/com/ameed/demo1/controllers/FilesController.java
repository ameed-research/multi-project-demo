package com.ameed.demo1.controllers;

import com.azure.storage.file.share.ShareClient;
import com.azure.storage.file.share.models.ShareFileItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/test/files")
@RequiredArgsConstructor
@Slf4j
public class FilesController {

    private final ShareClient shareClient;

    @GetMapping("/list")
    public Map<String, Object> listFilesInShare() {
        return shareClient.getRootDirectoryClient()
                .listFilesAndDirectories()
                .stream().collect(Collectors.toMap(
                        ShareFileItem::getName,
                        fileRef -> fileRef.isDirectory() ? "Directory" : "File"
                ));
    }

    @GetMapping("/create")
    public Map<String, Object> createTestFile() {
        var content = "Test create file";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
        String fileName = "create-test-%s.txt".formatted(new Random().nextInt(1000));
        shareClient.createFile(fileName, content.length())
                .upload(inputStream, content.length(), null);
        log.info("File {} created", fileName);
        return Map.of("fileName", fileName);
    }

    @GetMapping("/get")
    public Map<String, Object> getFileContent() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        shareClient.getFileClient("create-test.txt")
                .download(outputStream);
        return Map.of("content", outputStream.toString(StandardCharsets.UTF_8));
    }
}
